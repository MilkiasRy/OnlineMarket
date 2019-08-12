package waa.edu.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class BrandController {

    @GetMapping("/brands")
    public String gellAllBrands(Principal principal){
        return null;
    }
}
