package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;

import java.util.List;

public interface BuyerService {
    public Buyer save(Buyer buyer);
    public Buyer findById(Long id);
    public List<Buyer> findAll();
    public void deleteById(Long id);
    public Buyer findByCredential(Credential credential);
}
