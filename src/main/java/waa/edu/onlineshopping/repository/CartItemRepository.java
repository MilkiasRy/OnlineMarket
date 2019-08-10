package waa.edu.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Cart;
import waa.edu.onlineshopping.domain.CartItem;
import waa.edu.onlineshopping.domain.Product;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

         @Query(value = "select c from CartItem  c where c.cart=:cart")
          List<CartItem> findByCart(@Param("cart") Cart cart);

          @Query(value = "select c from CartItem c where c.cart=:cart and c.product=:product ")
          CartItem findCartItemByCartAndProduct(@Param("cart") Cart cart,@Param("product") Product product);


}
