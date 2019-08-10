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
    public Buyer findById(Long id) {
        return null;
    }

    @Override
    public List<Buyer> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Buyer findByCredential(Credential credential) {
        return buyerRepository.findByCredential(credential);
    }
}
