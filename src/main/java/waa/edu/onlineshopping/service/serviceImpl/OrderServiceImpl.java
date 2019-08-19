package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.repository.CartItemRepository;
import waa.edu.onlineshopping.repository.OrderRepository;
import waa.edu.onlineshopping.service.BuyerService;
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
    @Autowired
    BuyerService buyerService;
    @Override
    public Orders createOrder(Cart cart, BillingAddress billingAddress, Buyer buyer) {
        Buyer buyer1=null;
        if(billingAddress!=null ){
                buyer.addAddress(billingAddress.getAddress());
                buyer.setPayment(billingAddress.getPayment());
                 buyer1= buyerService.save(buyer);
            }



       Orders order=new Orders();
        order.setStatus(false);
        order.setBuyer(buyer1);
        order.setOrderdate(LocalDate.now());
        order.setOrderTotal(cart.getGrandTotal());

        List<CartItem> cartItemList =cartService.findByCart(buyer.getCart());

        for(CartItem cartItem : cartItemList) {
            Product product = cartItem.getProduct();
            cartItem.setOrder(order);
            product.setQuantity(product.getQuantity()- cartItem.getQuantity());

             cartItemRepository.save(cartItem);
        }

        Orders order1=orderRepository.save(order);



        return order1;
    }

    @Override
    public List<Orders> findByBuyer(Buyer buyer) {

        return orderRepository.findByBuyer(buyer);
    }
}
