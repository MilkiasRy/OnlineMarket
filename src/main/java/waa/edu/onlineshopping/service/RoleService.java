package waa.edu.onlineshopping.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import waa.edu.onlineshopping.domain.Role;

public interface RoleService {
    Role findByRole(String role);
}
