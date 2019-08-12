package waa.edu.onlineshopping.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BillingAddress {

    private Address address;
    private Payment payment;
}
