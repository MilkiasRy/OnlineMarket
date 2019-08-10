package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.domain.Role;
import waa.edu.onlineshopping.repository.BuyerRepository;
import waa.edu.onlineshopping.repository.CredentialRepository;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CredentialService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("custom")
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential userEntity = credentialRepository.findByEmail(username);

        if(userEntity == null) throw new UsernameNotFoundException(username);

        List<Role> roles = userEntity.getRoles().stream().collect(Collectors.toList());

        return new User(userEntity.getEmail(), userEntity.getPassword(), userEntity.isEnabled(), true,
                true, true, roles);
    }

    public Credential findByEmail(String email){
        return credentialRepository.findByEmail(email);
    }

    @Override
    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }

    @Override
    public List<Credential> findByEnabledFalse() {
        return credentialRepository.findByEnabledFalse();
    }

    @Override
    public Credential findById(Long id) {
        return credentialRepository.findById(id).get();
    }

    @Override
    public List<Credential> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        credentialRepository.deleteById(id);
    }
}
