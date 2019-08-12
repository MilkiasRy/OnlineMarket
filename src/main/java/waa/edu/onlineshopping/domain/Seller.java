package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @Cascade({CascadeType.ALL})
    @OneToMany(fetch=FetchType.LAZY, mappedBy="seller")
    List<Product> products;

    private int phonenumber;

    @Cascade(CascadeType.ALL)
    @OneToOne
    private Address addresses;

    @Cascade(CascadeType.ALL)
    @OneToOne
    private User user;

    @OneToMany(fetch=FetchType.LAZY)
    private List<Buyer> buyers;

    public void addBuyer(Buyer buyer) {
        if (buyers==null) {
            buyers = new ArrayList<Buyer>();
            buyers.add(buyer);

        } else {
            buyers.add(buyer);

        }
        //  return cartItems;
    }


}
