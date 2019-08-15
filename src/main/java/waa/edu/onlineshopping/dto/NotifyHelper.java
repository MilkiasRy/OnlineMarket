package waa.edu.onlineshopping.dto;

public class NotifyHelper {
    private Long id;
    private String description;
    private String productName;
    private String company;
    private int size;

    public NotifyHelper() {
    }

    public NotifyHelper(Long id, String description, String productName, String company) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "NotifyHelper{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", productName='" + productName + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
