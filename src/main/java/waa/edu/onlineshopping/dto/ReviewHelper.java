package waa.edu.onlineshopping.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import waa.edu.onlineshopping.domain.ReviewStatus;

@Data

public class ReviewHelper {

     private Long id;
    private String description;
    private Long  userid;
    private Long  productid;
    private Long  sellerid;
    private ReviewStatus reviewStatus;

    public ReviewHelper() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public Long getSellerid() {
        return sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }
}
