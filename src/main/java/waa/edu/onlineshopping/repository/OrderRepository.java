package waa.edu.onlineshopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Orders;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Orders,Long> {

        //List<Orders> findB(Buyer buyer);
}
