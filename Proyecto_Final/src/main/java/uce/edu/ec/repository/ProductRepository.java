package uce.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import uce.edu.ec.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByNameAndMaterial(String name, String material);
}
