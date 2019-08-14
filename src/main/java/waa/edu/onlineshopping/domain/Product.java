package waa.edu.onlineshopping.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String model;
    private int quantity;
    private double unitPrice;
    private double discountPrice;
    private String description;
    @Transient
    private MultipartFile productPic;

    @JoinColumn(name="SELLER_ID")
    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public double getDiscountPrice() {
//        return discountPrice;
//    }
//
//    public void setDiscountPrice(double discountPrice) {
//        this.discountPrice = discountPrice;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public MultipartFile getProductPic() {
//        return productPic;
//    }
//
//    public void setProductPic(MultipartFile productPic) {
//        this.productPic = productPic;
//    }
//
//    public Seller getSeller() {
//        return seller;
//    }
//
//    public void setSeller(Seller seller) {
//        this.seller = seller;
//    }
//
//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }
}
