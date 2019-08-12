package waa.edu.onlineshopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders,Long> {

        Orders findByBuyer(Buyer buyer);
}
