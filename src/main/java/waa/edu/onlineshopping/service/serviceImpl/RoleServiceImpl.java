package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.domain.Role;
import waa.edu.onlineshopping.repository.CredentialRepository;
import waa.edu.onlineshopping.repository.RoleRepository;
import waa.edu.onlineshopping.service.CredentialService;
import waa.edu.onlineshopping.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("custom")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String role){
        return roleRepository.findByRole(role);
    }



}
