package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.Cart;
import waa.edu.onlineshopping.domain.CartItem;

public interface CartItemService {
    public double updateCartItem(CartItem cartItem);
    public CartItem findById(Long id);

}
