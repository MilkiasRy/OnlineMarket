package waa.edu.onlineshopping.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.util.ArrayList;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Buyer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String gender;
    private String securityQuestion;
    private String securityAnswer;

    private int points;
    @JsonIgnore
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;
     @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
   //  @JsonBackReference
    @OneToOne(mappedBy = "buyer")
    //@JoinColumn
    private Cart cart;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    private List<Orders> orderList;

    @OneToMany(fetch = FetchType.LAZY)
    List<Notification> notificationList;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private Credential credential;

    public void addAddress(Address address) {
        if (address == null) {
            addresses = new ArrayList<Address>();
            addresses.add(address);

        } else {
            addresses.add(address);

        }
    }
    public void addNotification(Notification notification) {
        if (notification == null) {
            notificationList = new ArrayList<Notification>();
            notificationList.add(notification);

        } else {
            notificationList.add(notification);

        }
    }

}
