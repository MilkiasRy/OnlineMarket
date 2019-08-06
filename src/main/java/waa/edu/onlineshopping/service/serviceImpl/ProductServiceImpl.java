package waa.edu.onlineshopping.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import waa.edu.onlineshopping.domain.Product;
import waa.edu.onlineshopping.repository.ProductRepository;
import waa.edu.onlineshopping.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public void save(Product product) {
          productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {

        return (List<Product>) productRepository.findAll();
    }



    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
    }

    @Override
    public void deleteById(Long id) {
         Product product=  productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        productRepository.delete(product);
    }


}
