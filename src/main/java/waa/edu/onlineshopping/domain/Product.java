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

}
