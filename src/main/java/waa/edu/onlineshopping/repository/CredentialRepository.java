package waa.edu.onlineshopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;

import java.util.List;

@Repository
public interface CredentialRepository extends CrudRepository<Credential, Long> {

    Credential findByEmail(String email);
    List<Credential> findByEnabledFalse();
}
