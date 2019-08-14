package waa.edu.onlineshopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.domain.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {

             Seller findBySubscribedBuyers(Buyer buyer);
    public Seller findByCredential(Credential credential);

}
