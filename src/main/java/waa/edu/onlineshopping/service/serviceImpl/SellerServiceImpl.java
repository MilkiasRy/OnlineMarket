package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.edu.onlineshopping.domain.Seller;
import waa.edu.onlineshopping.repository.SellerRepository;
import waa.edu.onlineshopping.repository.SellerRepository;
import waa.edu.onlineshopping.service.SellerService;
import waa.edu.onlineshopping.service.SellerService;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller save(Seller Seller) {
        return sellerRepository.save(Seller);
    }

    @Override
    public Seller findById(Long id) {
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
