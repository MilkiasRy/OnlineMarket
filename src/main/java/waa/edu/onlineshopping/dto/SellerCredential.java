package waa.edu.onlineshopping.dto;

public class SellerCredential {

    private Long serialNumber;
    private Long credentialId;
    private String email;
    private String companyName;
    private String companyAddress;
    private Boolean accountEnabled;

    public SellerCredential(){}

    public SellerCredential(Long serialNumber, Long credentialId, String email, String companyName, String companyAddress, Boolean accountEnabled) {
        this.serialNumber = serialNumber;
        this.credentialId = credentialId;
        this.email = email;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.accountEnabled = accountEnabled;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public SellerCredential setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public SellerCredential setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SellerCredential setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public SellerCredential setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public SellerCredential setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
        return this;
    }

    public Boolean getAccountEnabled() {
        return accountEnabled;
    }

    public SellerCredential setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
        return this;
    }
}
