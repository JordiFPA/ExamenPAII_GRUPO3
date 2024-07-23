package uce.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.edu.ec.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
