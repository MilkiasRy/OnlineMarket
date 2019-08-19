package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Cart;
import waa.edu.onlineshopping.domain.CartItem;
import waa.edu.onlineshopping.domain.Product;

import java.util.List;

public interface CartService {

    public Cart addCart(Product product, Buyer buyer, int qty);

    public Cart findCartByBuyer(Buyer buyer);

     List<CartItem> findByCart(Cart cart);

     public void save(Cart cart);

     public void clearCart(Cart cart);

    public Cart findById(Long id);

}
