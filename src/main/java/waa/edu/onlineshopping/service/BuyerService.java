package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Cart;

import java.util.List;

public interface BuyerService {

    public void save(Buyer buyer);
    public List<Buyer> getBuyers();
    public Buyer findById(Long id);
    public void deleteById(Long id);


}
