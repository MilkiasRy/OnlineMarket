package waa.edu.onlineshopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

}
