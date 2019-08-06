package waa.edu.onlineshopping.service;

import waa.edu.onlineshopping.domain.Product;

import java.util.List;

public interface ProductService {

        public void save(Product product);
        public List<Product> getProducts();
        public Product findById(Long id);
        public void deleteById(Long id);
}
