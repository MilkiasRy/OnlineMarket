package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private int quantity;
    @OneToOne
    private Product product;
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name="cart_cartitem_id")
    private Cart cart;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private Orders order;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                ", totalPrice=" + totalPrice +
               '}';
    }
}
