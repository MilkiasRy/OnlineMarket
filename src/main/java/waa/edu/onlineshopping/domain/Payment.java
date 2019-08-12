package waa.edu.onlineshopping.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private String cardName;
	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvc;
	private String holderName;
	private boolean defaultPayment;
	

	
}
