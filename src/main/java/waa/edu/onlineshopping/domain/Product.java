package waa.edu.onlineshopping.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

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
    private double unitprice;
    private double discountPrice;
    private String description;
    @Transient
    private MultipartFile productpic;

    @Cascade({CascadeType.ALL})
    @JoinColumn(name="SELLER_ID")
    @ManyToOne (fetch = FetchType.LAZY)
    private Seller seller;
}
