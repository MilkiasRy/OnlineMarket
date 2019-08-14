package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.Buyer;
import waa.edu.onlineshopping.domain.Credential;
import waa.edu.onlineshopping.repository.BuyerRepository;
import waa.edu.onlineshopping.service.BuyerService;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Buyer save(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Override
    public List<Buyer> findAll() {
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

    @Override
    public Buyer findByCredential(Credential credential) {
        return buyerRepository.findByCredential(credential);
    }


}
