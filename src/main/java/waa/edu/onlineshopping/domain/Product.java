package waa.edu.onlineshopping.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
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
    private double unitprice;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();
//    private MultipartFile productpic;
//    @Cascade(CascadeType.ALL)
//    @OneToOne
//    private Seller company;
}
