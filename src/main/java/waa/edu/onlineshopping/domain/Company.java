package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
//@Entity
public class Company {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    @Cascade({CascadeType.ALL})
    @OneToMany(fetch=FetchType.EAGER)
    List<Product> products;
    private int phonenumber;
    @OneToOne
    private Address address;
    @OneToMany
    private List<String> emails;

}
