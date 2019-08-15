package waa.edu.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.domain.Seller;
import waa.edu.onlineshopping.service.BuyerService;
import waa.edu.onlineshopping.service.CredentialService;
import waa.edu.onlineshopping.service.SellerService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/buyer")
public class BrandController {
    @Autowired
    CredentialService credentialService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    SellerService sellerService;

    @Secured("ROLE_BUYER")
    @GetMapping("/brands")
    public String SellerShelf(Model model,Principal principal) {

        Credential credential = credentialService.findByEmail(principal.getName());
        Buyer buyer = buyerService.findByCredential(credential);
          if(principal.getName()!=null) {
              List<Seller> sellers = (List<Seller>) sellerService.findAll();
              model.addAttribute("sellers",sellers);
          }
          else
              return "/login";
          return "buyer/listOfSellers";
    }

    @Secured("ROLE_BUYER")
    @GetMapping("/brands/add/{id}")
    public @ResponseBody Boolean followBuyer(@PathVariable("id") Long id,Principal principal){
        Credential credential = credentialService.findByEmail(principal.getName());
        Buyer buyer = buyerService.findByCredential(credential);
          Seller seller=sellerService.findById(id);
          seller.addBuyer(buyer);
          sellerService.save(seller);
        System.out.println("++++++++++++++++++++++++++++++++sucesss");
        return true;
    }
    @Secured("ROLE_BUYER")
    @GetMapping("/brands/delete/{id}")
    public @ResponseBody Boolean unfollowBuyer(@PathVariable("id") Long id,Principal principal){
        Credential credential = credentialService.findByEmail(principal.getName());
        Buyer buyer = buyerService.findByCredential(credential);
        Seller seller=sellerService.findBySubscribedBuyers(buyer);
        seller.removeBuyer(buyer);
        sellerService.save(seller);
        System.out.println("************************Buyer deleted");
        //seller.addBuyer(buyer);
        return false;
    }
}
