package uce.edu.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.model.Product;
import uce.edu.ec.repository.ProductRepository;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductByNameAndMaterial(String name, String material) {
        return productRepository.findByNameAndMaterial(name, material);
    }

}
