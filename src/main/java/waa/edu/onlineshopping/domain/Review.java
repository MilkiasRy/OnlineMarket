package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Review{
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
}
