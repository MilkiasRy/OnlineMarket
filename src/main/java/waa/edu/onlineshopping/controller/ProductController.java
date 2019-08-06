package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import waa.edu.onlineshopping.domain.Product;
import waa.edu.onlineshopping.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

        @GetMapping({"/product"})
    public String getProduct(@ModelAttribute("product")Product product){

            return "product";
        }

    @PostMapping({"/product"})
    public String saveProduct(@Valid Product product, BindingResult bindingResult){
            if(bindingResult.hasErrors()){
                  return "product";
            }
         productService.save(product);
            return "product";
    }

    @GetMapping("/product/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
          model.addAttribute(product);
           return "product";
    }
    @PostMapping("/product/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Product product,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            product.setId(id);
               return "product";
        }

        productService.save(product);
        model.addAttribute("users", productService.getProducts());
        return "products";
    }

    @GetMapping("/products")
    public String getAllProducts(Model model){
              return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.deleteById(id);
                return "products";

    }
    @ModelAttribute("products")
    public List<Product> getAllProduct()
    {
          return productService.getProducts();
    }
}
