package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Seller;

import java.util.List;

public interface SellerService {
    public Seller save(Seller seller);
    public Seller findById(Long id);
    public List<Seller> findAll();
    public void deleteById(Long id);
}
