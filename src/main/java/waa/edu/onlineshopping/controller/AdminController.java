package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.domain.Seller;
import waa.edu.onlineshopping.dto.SellerCredential;
import waa.edu.onlineshopping.service.CredentialService;
import waa.edu.onlineshopping.service.SellerService;
import waa.edu.onlineshopping.util.EmailNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private EmailNotification emailNotification;

    @Autowired
    SellerService sellerService;

    @RequestMapping("/admin/inactive")
    public String sellerAccounts(Model model){
        List<Credential> credentials = credentialService.findByEnabledFalse();
        List<Seller> sellers = credentials.stream().map(c -> sellerService.findByCredential(c)).collect(Collectors.toList());
        List<SellerCredential> sc = new ArrayList<>();
        long count = 0;
        for(Seller sell: sellers){
            SellerCredential s = new SellerCredential();
            s.setSerialNumber(++count)
                    .setAccountEnabled(sell.getCredential().isEnabled())
                    .setCompanyAddress(sell.getAddress().toString())
                    .setCompanyName(sell.getName())
                    .setEmail(sell.getCredential().getEmail())
                    .setCredentialId(sell.getCredential().getId());
            sc.add(s);

        }

        model.addAttribute("sellerCredentials", sc);
        return "admin/activateSeller";
    }

    @RequestMapping("/admin/activate/seller")
    public String activateSeller(@RequestParam("sellerId") Long sellerId){
        Credential credential = credentialService.findById(sellerId);
        credential.setEnabled(true);
        credentialService.save(credential);

        emailNotification.sendEmail(credential.getEmail(), "Account Acctivated",
                "<p>Your account has been approved and activated. You can now login and post your products.</p>" +
                        "<p>Login Page: <a href=\"http://localhost:8080/login\">Online Shopping</a></p>");

        return "redirect:/admin/inactive";
    }

    @RequestMapping("/admin/pending/reviews")
    public String pendingReviews(Model model){
        model.addAttribute("sellerCredentials", credentialService.findByEnabledFalse());
        return "admin/activateSeller";
    }
}
