package waa.edu.onlineshopping.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonManagedReference
   // @Cascade(CascadeType.ALL)
    @OneToMany(mappedBy="cart",  fetch=FetchType.EAGER)
    @JsonIgnore
    private List<CartItem> cartItems;

     @JsonIgnore
    @JsonBackReference
    @Cascade({CascadeType.ALL})
    @OneToOne(fetch = FetchType.LAZY)
    private Buyer buyer;



    private double grandTotal;

    public void addCartItem(CartItem item) {
        if (cartItems==null) {
            cartItems = new ArrayList<CartItem>();
            cartItems.add(item);

        } else {
            cartItems.add(item);

        }
      //  return cartItems;
    }
}