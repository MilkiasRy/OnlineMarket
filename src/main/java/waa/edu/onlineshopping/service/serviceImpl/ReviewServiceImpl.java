package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import waa.edu.onlineshopping.domain.Review;
import waa.edu.onlineshopping.domain.ReviewStatus;
import waa.edu.onlineshopping.domain.Seller;
import waa.edu.onlineshopping.dto.NotifyHelper;
import waa.edu.onlineshopping.dto.ReviewHelper;
import waa.edu.onlineshopping.repository.ReviewRepository;
import waa.edu.onlineshopping.service.ReviewService;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid buyer Id:" + id));
    }

    @Override
    public List<Review> findAll() {
        return (List<Review>) reviewRepository.findAll();
    }

    @Override
    public Flux<ReviewHelper> findReviewBySeller(Seller seller) {

         List<ReviewHelper> helperList=new ArrayList<>();
             for(ReviewHelper reviewHelper:findByReviewStatus_Pending(ReviewStatus.APPROVED)){
                       if(reviewHelper.getSellerid()==seller.getId()){
                           System.out.println("********************");
                             helperList.add(reviewHelper);
                       }
             }
             return Flux.fromIterable(helperList);
    }

    @Override
    public List<ReviewHelper> findByReviewStatus_Pending(ReviewStatus status) {

          List<ReviewHelper> helperArrayList=new ArrayList<>();
         for(Review review: reviewRepository.findByReviewStatus_Pending(status)){
                 ReviewHelper reviewHelper=new ReviewHelper();
                 reviewHelper.setDescription(review.getDescription());
                 reviewHelper.setProductid(review.getProduct().getId());
                 reviewHelper.setSellerid(review.getProduct().getSeller().getId());
                 reviewHelper.setUserid(review.getBuyer().getId());
                 reviewHelper.setId(review.getId());
                 reviewHelper.setReviewStatus(review.getReviewStatus());
             System.out.println("++++++++++++++++++++++++++++++++++++++");
             System.out.println(reviewHelper);
                 helperArrayList.add(reviewHelper);
        }
        return helperArrayList;
    }
}
