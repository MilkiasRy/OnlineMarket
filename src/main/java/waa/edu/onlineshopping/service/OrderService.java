package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.*;

import java.util.List;

public interface OrderService {
    public Orders createOrder(Cart cart, BillingAddress billingAddress, Buyer buyer);
    public List<Orders> findByBuyer(Buyer buyer);
}
