package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.repository.CartItemRepository;
import waa.edu.onlineshopping.repository.OrderRepository;
import waa.edu.onlineshopping.service.CartItemService;
import waa.edu.onlineshopping.service.CartService;
import waa.edu.onlineshopping.service.OrderService;

import java.time.LocalDate;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartService cartService;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Orders createOrder(Cart cart, BillingAddress billingAddress, Buyer buyer) {
            if(billingAddress!=null ){
                buyer.addAddress(billingAddress.getAddress());
                buyer.setPayment(billingAddress.getPayment());
            }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(buyer.getAddresses());
        System.out.println(buyer.getPayment());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
       Orders order=new Orders();
        order.setStatus(false);
        order.setBuyer(buyer);
        order.setOrderdate(LocalDate.now());
        order.setOrderTotal(cart.getGrandTotal());

        List<CartItem> cartItemList =cartService.findByCart(buyer.getCart());

        for(CartItem cartItem : cartItemList) {
            Product product = cartItem.getProduct();
            cartItem.setOrder(order);
            product.setQuantity(product.getQuantity()- cartItem.getQuantity());

             cartItemRepository.save(cartItem);
        }
           //order.setCartItemList(cartItemList);


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(cart.getGrandTotal());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return order;
    }

    @Override
    public Orders findByBuyer(Buyer buyer) {
        return orderRepository.findByBuyer(buyer);
    }
}
