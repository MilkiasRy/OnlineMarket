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



        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
       Orders order=new Orders();
        order.setStatus(false);
        order.setBuyer(buyer1);
        order.setOrderdate(LocalDate.now());
        order.setOrderTotal(cart.getGrandTotal());

        System.out.println("++++++++++++++++++++++++cartItem++++++++++++++++++++++++++++++++++++++++");

        List<CartItem> cartItemList =cartService.findByCart(buyer.getCart());
        System.out.println("before order ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        for(CartItem cartItem : cartItemList) {
            Product product = cartItem.getProduct();
            cartItem.setOrder(order);
            product.setQuantity(product.getQuantity()- cartItem.getQuantity());

             cartItemRepository.save(cartItem);
        }
           //order.setCartItemList(cartItemList);


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("sucessfull ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return order;
    }

    @Override
    public List<Orders> findAll() {
        return (List<Orders>)orderRepository.findAll();
    }
}
