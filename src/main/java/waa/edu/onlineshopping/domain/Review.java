package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//@Data
//@NoArgsConstructor
@Entity
public class Review implements Serializable {

    public Review() {
    }

    public Review(String description, ReviewStatus reviewStatus, Buyer buyer, Product product) {
        this.description = description;
        this.reviewStatus = reviewStatus;
        this.buyer = buyer;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String description;

    private ReviewStatus reviewStatus = ReviewStatus.PENDING;

    @OneToOne
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private  Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
