package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
@Data
@NoArgsConstructor
//@Entity
public class Cart {
    @OneToMany
    List<OrderLine> orderLines;
}
