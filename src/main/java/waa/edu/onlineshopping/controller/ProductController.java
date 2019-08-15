package waa.edu.onlineshopping.controller;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.exception.ImageNotFoundException;
import waa.edu.onlineshopping.service.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/seller")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    CredentialService credentialService;
    @Autowired
    SellerService sellerService;
    @Autowired
    BuyerService buyerService;

        @GetMapping({"/product"})
    public String getProduct(@ModelAttribute("product")Product product){

            return "seller/product";
        }

    @PostMapping({"/product"})
    public String saveProduct(@Valid Product product, BindingResult bindingResult, HttpServletRequest request, Principal principal){
        Credential credential = credentialService.findByEmail(principal.getName());
        Seller seller = sellerService.findByCredential(credential);
              Long newProduct=product.getId();
            if(bindingResult.hasErrors()){
                  return "seller/product";
            }
        MultipartFile memberImage = product.getProductPic();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
                product.setSeller(seller);
             Product p=productService.save(product);

              List<Buyer> subscribedBuyers= seller.getSubscribedBuyers();
              if(newProduct==null) {
                  for (Buyer buyer : subscribedBuyers) {
                      Notification notification = new Notification();
                      notification.setDescription(p.getDescription());
                      notification.setCompany(seller);
                      notification.setProduct(product);
                      Notification notification1 = notificationService.save(notification);
                      buyer.addNotification(notification1);
                      buyerService.save(buyer);
                  }
              }

        if (memberImage != null && !memberImage.isEmpty()) {

            try {
                           	if(p!=null){

                memberImage.transferTo(

                             new File(rootDirectory + p.getId() + ".png"));
            }
            }catch (Exception e) {
                  productService.deleteById(p.getId());
                throw new ImageNotFoundException("Image failed");
            }
        }
        else{
            System.out.println("Image failed");
           // throw new IllegalArgumentException();
        }
             return "redirect:/seller/products";
    }

    @GetMapping("/product/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
          model.addAttribute(product);
//        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//
//        model.addAttribute("qtyList", qtyList);
//        model.addAttribute("qty", 1);
           return "seller/product";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model){
        return "seller/products";
    }

    @GetMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.deleteById(id);
                return "redirect:/seller/products";

    }
    @ModelAttribute("products")
    public List<Product> getAllProduct()
    {
          return productService.getProducts();
    }







}
