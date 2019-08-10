package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
   @Cascade(CascadeType.ALL)
    @OneToOne
    private User user;

    private int points;

    @OneToMany
    private List<Address> addresses;
    @Cascade(CascadeType.ALL)
    @OneToOne
    private Payment payment;
    @OneToOne(mappedBy = "buyer")
    //@JoinColumn
    private Cart cart;
//    @OneToOne(mappedBy = "order")
//    private Order order;

}