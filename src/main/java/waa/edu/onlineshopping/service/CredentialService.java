package waa.edu.onlineshopping.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;

import java.util.List;

public interface CredentialService extends UserDetailsService {

    public Credential save(Credential credential);
    List<Credential> findByEnabledFalse();
    public Credential findById(Long id);
    public List<Credential> findAll();
    public void deleteById(Long id);
    public Credential findByEmail(String email);

}
