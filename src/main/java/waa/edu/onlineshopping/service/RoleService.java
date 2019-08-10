package waa.edu.onlineshopping.service;




import waa.edu.onlineshopping.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role get(Long id);
}
