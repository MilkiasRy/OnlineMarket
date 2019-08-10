package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Cart;
import waa.edu.onlineshopping.repository.BuyerRepository;
import waa.edu.onlineshopping.service.BuyerService;

import java.util.List;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {



    @Autowired
    BuyerRepository buyerRepository;


   @Override
    public void save(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    @Override
    public List<Buyer> getBuyers() {

        return (List<Buyer>) buyerRepository.findAll();
    }



    @Override
    public Buyer findById(Long id) {
        return buyerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid buyer Id:" + id));
    }

    @Override
    public void deleteById(Long id) {
        Buyer buyer=  buyerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid buyer Id:" + id));

        buyerRepository.delete(buyer);
    }

}
