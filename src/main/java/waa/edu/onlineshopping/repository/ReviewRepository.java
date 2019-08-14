package waa.edu.onlineshopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import waa.edu.onlineshopping.domain.Review;
import waa.edu.onlineshopping.domain.ReviewStatus;

import java.util.List;


@Repository
public interface ReviewRepository extends CrudRepository<Review,Long> {
      @Query(value = "SELECT r from Review r where r.reviewStatus=:status")
      List<Review> findByReviewStatus_Pending(@Param("status") ReviewStatus status);


}
