//package waa.edu.onlineshopping.domain;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
////@Entity
//public class Admin {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//    @Cascade(CascadeType.ALL)
//    @OneToOne
//    private User user;
//    //create a query those who are in active
//
//    private List<Seller> sellers;
//
//
//}
