package waa.edu.onlineshopping.dto;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
public class TableDto {
    String id;
    int quantity;
    double totalprice;
    double price;
    String productname;


    public TableDto() {
    }

    public TableDto(int quantity, double totalprice, double price, String productname) {
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.price = price;
        this.productname = productname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

     public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
