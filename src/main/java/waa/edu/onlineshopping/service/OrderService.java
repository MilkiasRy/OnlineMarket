package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.*;

public interface OrderService {
    public Orders createOrder(Cart cart, BillingAddress billingAddress, Buyer buyer);
    public Orders findByBuyer(Buyer buyer);
}
