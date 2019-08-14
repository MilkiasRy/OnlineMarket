package waa.edu.onlineshopping.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import waa.edu.onlineshopping.domain.*;
import waa.edu.onlineshopping.dto.NotifyHelper;
import waa.edu.onlineshopping.dto.ReviewHelper;
import waa.edu.onlineshopping.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class ReviewController {

    @Autowired
    CredentialService credentialService;
    @Autowired
    BuyerService buyerService;
    @Autowired
    ProductService productService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    SellerService sellerService;

    @PostMapping("/buyer/review/add/{productId}/{des}")
    public @ResponseBody Boolean postReview(@Valid
               @PathVariable("productId")Long productId, @PathVariable("des")String des, Principal principal) {
        Credential credential = credentialService.findByEmail(principal.getName());
        Buyer buyer = buyerService.findByCredential(credential);
        Review review=new Review();
          review.setBuyer(buyer);
          review.setDescription(des);
          review.setProduct(productService.findById(productId));
          Review review1=reviewService.save(review);
       // System.out.println(request.getParameter("review"));
         if(review1.getId()!=null)
        return true;
         else
             return false;
    }

    @RequestMapping("/admin/pending")
    public String sellerAccounts(Model model){
        model.addAttribute("pendingReviews", reviewService.findByReviewStatus_Pending(ReviewStatus.PENDING));
        return "admin/pendingReview";
    }
    @RequestMapping("/admin/save/{id}")
    public @ResponseBody
    Long saveReview(@PathVariable("id")Long id){
        Review review=reviewService.findById(id);
        review.setReviewStatus(ReviewStatus.APPROVED);
        reviewService.save(review);
        return id;
    }
     @RequestMapping("/admin/delete/{id}")
    public @ResponseBody Long deleteReview(@PathVariable("id") Long id){
        Review review=reviewService.findById(id);
        review.setReviewStatus(ReviewStatus.REJECTED);
        reviewService.save(review);
        return id;
     }

    @GetMapping(path = "/seller/reviews",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ReviewHelper> feed(Principal principal) {
        Credential credential = credentialService.findByEmail(principal.getName());
        Seller seller = sellerService.findByCredential(credential);
        return reviewService.findReviewBySeller(seller);
    }
         @GetMapping("/seller/review")
    public String getReview(){
           return "seller/review";
         }
}
