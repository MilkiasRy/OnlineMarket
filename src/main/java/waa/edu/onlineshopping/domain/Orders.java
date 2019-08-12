package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Buyer buyer;
    private LocalDate orderdate;
    private double orderTotal;

     @OneToMany(mappedBy = "order", cascade=CascadeType.ALL )
    private List<CartItem> cartItemList;

    private boolean status;
}
