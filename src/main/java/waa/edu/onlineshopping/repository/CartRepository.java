package waa.edu.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import waa.edu.onlineshopping.domain.Cart;
import waa.edu.onlineshopping.domain.CartItem;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {


}
