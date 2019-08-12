package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.service.CredentialService;
import waa.edu.onlineshopping.util.EmailNotification;

@Controller
public class AdminController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EmailNotification emailNotification;

    @RequestMapping("/")
    public String notification(Model model){
//        model.addAttribute("sellerCredentials", credentialService.findByEnabledFalse());
        return "home";
    }

    @RequestMapping("/inactive")
    public String sellerAccounts(Model model){
        model.addAttribute("sellerCredentials", credentialService.findByEnabledFalse());
        return "admin/activateSeller";
    }

    @RequestMapping("/activate/seller")
    public String activateSeller(@RequestParam("sellerId") Long sellerId){
        Credential credential = credentialService.findById(sellerId);
        credential.setEnabled(true);
        credentialService.save(credential);

        emailNotification.sendEmail(credential.getEmail(), "Account Acctivated",
                "<p>Your account has been approved and activated. You can now login and post your products.</p>" +
                        "<p>Login Page: <a href=\"http://localhost:8080/login\">Online Shopping</a></p>");

        return "redirect:/inactive";
    }

    @RequestMapping("/pending/reviews")
    public String pendingReviews(Model model){
        model.addAttribute("sellerCredentials", credentialService.findByEnabledFalse());
        return "admin/activateSeller";
    }
}
