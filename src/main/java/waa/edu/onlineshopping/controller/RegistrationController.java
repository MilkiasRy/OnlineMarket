package waa.edu.onlineshopping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Role;
import waa.edu.onlineshopping.domain.User;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.RoleService;
import waa.edu.onlineshopping.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BuyerService buyerService;

    @ModelAttribute("roles")
    public List<Role> getRoles(Model model) {
        return roleService.findAll();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@ModelAttribute("user") User user) {

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {

            user.getRoles().forEach(System.out::println);
            Buyer buyer=new Buyer();
            buyer.setUser(user);
             buyerService.save(buyer);
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
            return "registration";

        }

    }
}