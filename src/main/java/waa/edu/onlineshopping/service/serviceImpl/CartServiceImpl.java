package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Cart;
import waa.edu.onlineshopping.domain.CartItem;
import waa.edu.onlineshopping.domain.Product;
import waa.edu.onlineshopping.repository.CartItemRepository;
import waa.edu.onlineshopping.repository.CartRepository;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CartService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    BuyerService buyerService;
    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public Cart addCart(Product product, Buyer buyer, int qty) {

        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(qty);
         cartItem.setTotalPrice(product.getDiscountPrice()*qty);

                  Cart cart= buyer.getCart();
        if(cart==null)
        {
            Cart newcart=new Cart();
           newcart.setGrandTotal(cartItem.getTotalPrice());
           newcart.setBuyer(buyer);
         //   newcart.setOrderdate(LocalDate.now());
         //   newcart.setStatus(false);
                Cart cart1=cartRepository.saveAndFlush(newcart);
                cartItem.setCart(cart1);
               cartItemRepository.saveAndFlush(cartItem);
                return cart1;
        }
  else {
                 CartItem item = cartItemRepository.findCartItemByCartAndProduct(cart, product);
         if(item!=null) {
             if (item.getProduct().getId() == product.getId()) {
                 item.setQuantity(item.getQuantity() + qty);
                 item.setTotalPrice(item.getQuantity() * product.getDiscountPrice());
                 cart.setGrandTotal(item.getTotalPrice());
                 cartRepository.saveAndFlush(cart);

                 item.setCart(cart);
                 item.setId(item.getId());
                 cartItemRepository.saveAndFlush(item);

             }
         }
             if  ( item==null || item.getProduct().getId() != product.getId()) {
                 Cart cart1=cartRepository.findById(cart.getId()).get();
                 cart.setGrandTotal(cart1.getGrandTotal()+cartItem.getTotalPrice());
                 cart.setBuyer(buyer);
                  cartRepository.save(cart);
                 cartItem.setCart(cart);
                 cartItemRepository.save(cartItem);
             }

        }
            return  cart;
            }

    @Override
    public Cart findCartByBuyer(Buyer buyer) {
        Cart cart= buyer.getCart();

        return cartRepository.findById(cart.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid Cart Id:" + cart.getId()));

    }

    @Override
    public List<CartItem> findByCart(Cart cart) {

        return cartItemRepository.findByCart(cart);
    }
}
