package uce.edu.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.model.Administrator;
import uce.edu.ec.repository.AdministratorRepository;

import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    private final String DEFAULT_NAME = "admins";
    private final String DEFAULT_PASSWORD = "admin";

    public Administrator getAdministratorByNameAndPassword(String name, String password) throws Exception {
        if (name.equals(DEFAULT_NAME) && password.equals(DEFAULT_PASSWORD)) {
            return new Administrator(DEFAULT_NAME, DEFAULT_PASSWORD);
        } else {
            Administrator admin = administratorRepository.findByNameAndPassword(name, password);
            if (admin != null) {
                return admin;
            } else {
                throw new Exception("Nombre o contraseña incorrectos");
            }
        }
    }
}

