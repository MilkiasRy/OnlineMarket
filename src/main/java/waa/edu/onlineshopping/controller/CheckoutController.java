package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.service.*;
//import waa.edu.onlineshopping.util.EmailNotification;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CheckoutController {

@Autowired
    UserService userService;
@Autowired
BuyerService buyerService;
@Autowired
    CartItemService cartItemService;
@Autowired
    CartService cartService;
@Autowired
    OrderService orderService;
//@Autowired
//    EmailNotification emailNotification;


    @RequestMapping("/checkout")
    public String checkout(@ModelAttribute("newBillingAddress")BillingAddress billingAddress,@RequestParam("id") Long cartId,
                           @RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField,
                           Principal principal,Model model){
        User user  = userService.findUserByEmail(principal.getName());
          Buyer buyer= buyerService.findById(user.getBuyer().getId());


        if (cartId != buyer.getCart().getId()) {
            return "badRequestPage";
        }
        List<CartItem> cartItemList =cartService.findByCart(buyer.getCart());

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProduct().getQuantity() < cartItem.getQuantity()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/buyer/shoppingCart/cart";
            }
        }
        List<Address> buyerAddresses = buyer.getAddresses();
        Payment buyerPayment = buyer.getPayment();


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        model.addAttribute("buyerShippingList", buyerAddresses);
        System.out.println("Addresses"+ buyerAddresses);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        model.addAttribute("payment", buyerPayment);
       /// model.addAttribute("newBillingAddress",new BillingAddress());


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        if (buyerPayment == null) {
            model.addAttribute("emptyPayment", true);
        } else {
            model.addAttribute("emptyPayment", false);
        }

        if (buyerAddresses.size() == 0) {
            model.addAttribute("emptyShippingList", true);
        } else {
            model.addAttribute("emptyShippingList", false);
        }

        model.addAttribute("cartItemList", cartItemList);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("caritemList"+cartItemList);
        model.addAttribute("shoppingCart", user.getBuyer().getCart());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
       // System.out.println(buyer.getCart());
        model.addAttribute("classActiveShipping", true);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        if (missingRequiredField) {
            model.addAttribute("missingRequiredField", true);
        }

        return "buyer/checkout";
    }
    @PostMapping("/checkout")
    public String checkoutPost(@ModelAttribute("newBillingAddress") BillingAddress billingAddress,
                               @ModelAttribute("shippingMethod") String shippingMethod, Principal principal, Model model){
//           if(result.hasErrors()){
//               return "buyer/checkout";
//           }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(billingAddress);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        User user  = userService.findUserByEmail(principal.getName());
        Buyer buyer= buyerService.findById(user.getBuyer().getId());

        List<CartItem> cartItemList =cartService.findByCart(buyer.getCart());
        model.addAttribute("cartItemList",cartItemList);

         Cart cart=user.getBuyer().getCart();
        Orders order = orderService.createOrder(cart, billingAddress, buyer);
        Orders orders=orderService.findByBuyer(buyer);
        model.addAttribute("order_id",orders.getId());
//        emailNotification.sendEmail(principal.getName(),"Thank you for shopping on our book store. We hope you had a good time with our service!",
//                                    "ordernumber"+orders.getId());
        cartService.clearCart(cart);

        LocalDate today = LocalDate.now();
        LocalDate estimatedDeliveryDate;

        if (shippingMethod.equals("groundShipping")) {
            estimatedDeliveryDate = today.plusDays(5);
        } else {
            estimatedDeliveryDate = today.plusDays(3);
        }

        model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);


        return "buyer/confirmationPage";
    }

}