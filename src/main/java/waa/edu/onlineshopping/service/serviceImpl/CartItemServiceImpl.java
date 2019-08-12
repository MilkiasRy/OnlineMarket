package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.Cart;
import waa.edu.onlineshopping.domain.CartItem;
import waa.edu.onlineshopping.repository.CartItemRepository;
import waa.edu.onlineshopping.service.CartItemService;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

  @Autowired
    CartItemRepository cartItemRepository;
    @Override
    public double updateCartItem(CartItem cartItem) {
        Double qty=Double.valueOf(cartItem.getQuantity());
        Double discountPrice=cartItem.getProduct().getDiscountPrice();
       // double prevCartItem= cartItem.getTotalPrice();
        cartItem.setTotalPrice(qty*discountPrice);
        cartItemRepository.saveAndFlush(cartItem);
        System.out.println(cartItem.getCart().getId());
        return cartItemRepository.findGrandTotal(cartItem.getCart().getId());


    }

    @Override
    public CartItem findById(Long id) {
        return (CartItem) cartItemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid CartItem Id:" + id));
    }
}
