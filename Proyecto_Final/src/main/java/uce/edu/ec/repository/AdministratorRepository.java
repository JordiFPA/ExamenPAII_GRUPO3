package uce.edu.ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uce.edu.ec.model.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
