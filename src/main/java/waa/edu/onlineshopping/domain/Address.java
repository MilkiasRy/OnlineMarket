package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String street;
    private  String city;
    private  String state;
    private String zipcode;

    @Override
    public String toString() {
        return  street + ", " + city + ", " + state + "  " + zipcode;
    }
}
