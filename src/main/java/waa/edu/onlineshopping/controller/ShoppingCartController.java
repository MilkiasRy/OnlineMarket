package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute("product") Product product,
                          @ModelAttribute("qty") String qty, Model model, Principal principal, RedirectAttributes redirectAttributes
    ) {
        User user = userService.findUserByEmail(principal.getName());
        Buyer buyer = buyerService.findById(user.getBuyer().getId());

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

    @GetMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Buyer buyer = buyerService.findById(user.getBuyer().getId());
        Cart cart = buyer.getCart();
        List<CartItem> cartItemList = cartService.findByCart(cart);
        System.out.println(cartItemList);


        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", cart);

        return "buyer/shoppingCart";
    }
    @RequestMapping("/updateCartItem/{qty}/{cartItem_id}")
    public @ResponseBody Double updateShoppingCart(@PathVariable("qty") Integer qty ,@PathVariable("cartItem_id") Long id ) {
            CartItem cartItem =  cartItemService.findById(id);
       // System.out.println(cartItem);
        cartItem.setQuantity(qty);
        double grandTotal=cartItemService.updateCartItem(cartItem);
          Cart cart=cartItem.getCart();
          cart.setGrandTotal(grandTotal);
          cartService.save(cart);
          return grandTotal;
    }
}
