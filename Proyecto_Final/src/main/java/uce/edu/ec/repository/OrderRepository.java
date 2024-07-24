package uce.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uce.edu.ec.model.Orden;

public interface OrderRepository extends JpaRepository<Orden,Long> {
}
