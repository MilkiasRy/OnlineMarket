package waa.edu.onlineshopping.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class IndexController implements ErrorController{
    private final static String PATH = "/error";
    @Override
    @RequestMapping(PATH)
     public String getErrorPath() {
        // TODO Auto-generated method stub
        System.out.println("Name   "+getUserPrincipal().getName());
        System.out.println("=========================");
        System.out.println("Credentials  "+getAuthentication().getAuthorities());
        if(getAuthentication().getAuthorities().equals("ROLE_ADMIN"))
            System.out.println("admin");

        System.out.println("=========================");
        System.out.println("Credentials  "+getAuthentication().getName());
        System.out.println("=========================");
        System.out.println("Credentials  "+getAuthentication().getDetails());
        return "home";
    }
    public Principal getUserPrincipal() {
        Authentication auth = getAuthentication();
   //     System.out.println("Principal"+auth.getPrincipal());
        if ((auth == null) || (auth.getPrincipal() == null)) {
                      return null;
        }
        return auth;
    }

    //And the getAuthentication
    private Authentication getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("=========================");
        System.out.println("auth      "+auth);
//         if (isAnonymous(auth))
//             return null;
        return auth;
    }
    public boolean isAnonymous(Authentication authentication){
             return  true;
    }
}