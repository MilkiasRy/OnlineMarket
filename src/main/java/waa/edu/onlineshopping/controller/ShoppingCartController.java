package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.service.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/buyer/shoppingCart")
@SessionAttributes({"cart"})
public class ShoppingCartController {


    @Autowired
    CredentialService credentialService;
    @Autowired
    ProductService productService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;

    @Secured("ROLE_BUYER")
    @PostMapping("/addItem")
    public String addItem(@ModelAttribute("product") Product product,
                          @ModelAttribute("qty") String qty, Model model, Principal principal, RedirectAttributes redirectAttributes
    ) {
        Credential credential = credentialService.findByEmail(principal.getName());
//        Buyer buyer = buyerService.findById(user.getBuyer().getId());
        Buyer buyer = buyerService.findByCredential(credential);
        if (buyer != null) {


            model.addAttribute("buyer", buyer);

            product = productService.findById(product.getId());

            if (Integer.parseInt(qty) > product.getQuantity()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/product/" + product.getId();
            }

            Cart cart = cartService.addCart(product, buyer, Integer.parseInt(qty));

            redirectAttributes.addFlashAttribute("addProductSuccess", true);


            return "redirect:/product/" + product.getId();
        }
        else
            return "redirect:/login";
    }

    @Secured("ROLE_BUYER")
    @GetMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        Credential credential = credentialService.findByEmail(principal.getName());
        Buyer buyer = buyerService.findByCredential(credential);


                       Cart cart = buyer.getCart();
                       List<CartItem> cartItemList = cartService.findByCart(cart);


                       model.addAttribute("cartItemList", cartItemList);
                       model.addAttribute("shoppingCart", cart);

                               return "buyer/shoppingCart";

    }
    @Secured("ROLE_BUYER")
    @RequestMapping("/updateCartItem/{qty}/{cartItem_id}")
    public @ResponseBody Double updateShoppingCart(@PathVariable("qty") Integer qty ,@PathVariable("cartItem_id") Long id ) {
            CartItem cartItem =  cartItemService.findById(id);

        cartItem.setQuantity(qty);
        double grandTotal=cartItemService.updateCartItem(cartItem);
          Cart cart=cartItem.getCart();
          cart.setGrandTotal(grandTotal);
          cartService.save(cart);
          return grandTotal;
    }
}
