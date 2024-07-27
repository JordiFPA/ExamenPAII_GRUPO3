package uce.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.edu.ec.model.Customer;
import uce.edu.ec.model.Orden;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orden,Long> {
    List<Orden> findByCustomer(Customer customer);
}
