package waa.edu.onlineshopping.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String model;
     @NotEmpty
     private int quantity;
     @NotEmpty
     private double unitPrice;
    private double discountPrice;
    @NotEmpty
    private String description;
    @Transient
    private MultipartFile productPic;

    @JoinColumn(name="SELLER_ID")
    @ManyToOne (cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Seller seller;


    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();


}
