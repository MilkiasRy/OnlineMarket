package waa.edu.onlineshopping.service;


import waa.edu.onlineshopping.domain.User;

public interface UserService {
    User saveUser(User user);

    User findUserByEmail(String email);
}
