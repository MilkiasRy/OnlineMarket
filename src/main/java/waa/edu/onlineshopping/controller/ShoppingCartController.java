package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CartService;
import waa.edu.onlineshopping.service.ProductService;
import waa.edu.onlineshopping.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/buyer/shoppingCart")
@SessionAttributes({"cart"})
public class ShoppingCartController {


    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    CartService cartService;

    @PostMapping("/addItem")
       public String addItem(@ModelAttribute("product") Product product,
                          @ModelAttribute("qty") String qty, Model model, Principal principal, RedirectAttributes redirectAttributes
                          ) {
            User user = userService.findUserByEmail(principal.getName());
           Buyer buyer=buyerService.findById(user.getBuyer().getId());

           model.addAttribute("buyer",buyer);

           product = productService.findById(product.getId());

        if (Integer.parseInt(qty) > product.getQuantity()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/product/"+product.getId();
        }

  Cart cart= cartService.addCart(product,buyer, Integer.parseInt(qty));
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
//        System.out.println(cart.getId());

          redirectAttributes.addFlashAttribute("addProductSuccess", true);


           return "redirect:/product/"+product.getId();
    }

    @GetMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Buyer buyer=buyerService.findById(user.getBuyer().getId());
         Cart cart=buyer.getCart();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++=");
           List<CartItem> cartItemList=cartService.findByCart(cart);
        System.out.println(cartItemList);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++=");

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", cart);

        return "buyer/shoppingCart";
    }
}
