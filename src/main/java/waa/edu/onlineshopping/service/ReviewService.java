package waa.edu.onlineshopping.service;

import reactor.core.publisher.Flux;
import waa.edu.onlineshopping.domain.Review;
import waa.edu.onlineshopping.domain.ReviewStatus;
import waa.edu.onlineshopping.domain.Seller;
import waa.edu.onlineshopping.dto.ReviewHelper;

import java.util.List;

public interface ReviewService {
       Review save(Review review);
       Review findById(Long id);
       List<Review> findAll();
      List<ReviewHelper> findByReviewStatus_Pending(ReviewStatus status);
      Flux<ReviewHelper> findReviewBySeller(Seller seller);

}
