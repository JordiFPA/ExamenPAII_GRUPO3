package uce.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uce.edu.ec.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
