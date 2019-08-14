package waa.edu.onlineshopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.domain.Product;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CredentialService;
import waa.edu.onlineshopping.service.ProductService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {


    @Autowired
    private CredentialService credentialService;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String productshelf(Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            System.out.println(username);
            Credential credential = credentialService.findByEmail(username);
            Buyer user = buyerService.findByCredential(credential);
          //  System.out.println("LOGGED IN USER " + user);
            model.addAttribute("user", user);
        }

        List<Product> products = productService.getProducts();
      if(products.isEmpty()||products==null){
        model.addAttribute("emptyList",true);
      }
        model.addAttribute("productList", products);
        model.addAttribute("activeAll",true);

        return "/buyer/home";
    }




    @GetMapping("/product/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute(product);
        List<Integer> qtyList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        model.addAttribute("qtyList", qtyList);
        model.addAttribute("qty", 1);
        return "buyer/productdetail";
    }
}
