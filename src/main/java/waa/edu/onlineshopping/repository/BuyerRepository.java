package waa.edu.onlineshopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, Long> {

    public Buyer findByCredential(Credential credential);
}
