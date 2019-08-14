package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import javax.persistence.*;
import javax.validation.Valid;
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
    private String securityQuestion;
    private String securityAnswer;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "seller")
    List<Product> products;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Buyer> subscribedBuyers;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Credential credential;

    public void addBuyer(Buyer buyer) {
        if (subscribedBuyers==null) {
            subscribedBuyers = new ArrayList<Buyer>();
            subscribedBuyers.add(buyer);

        } else {
            subscribedBuyers.add(buyer);

        }
    }
     public void removeBuyer(Buyer buyer){
           subscribedBuyers.remove(buyer);
     }
}
