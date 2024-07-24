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

    public synchronized void saveAdministrator(Administrator administrator) throws Exception {
        validateAdministratorFields(administrator);

        if (administratorRepository.findByName(administrator.getName()).isPresent()) {
            throw new Exception("El administrador ya existe");
        }
        administratorRepository.save(administrator);
    }

    private void validateAdministratorFields(Administrator administrator) throws Exception {
        if (administrator.getName().isBlank() || administrator.getPassword().isBlank()) {
            throw new Exception("Todos los campos son obligatorios");
        }

        if (!administrator.getName().matches("[a-zA-Z]+")) {
            throw new Exception("El nombre solo puede contener letras");
        }

        if (administrator.getPassword().length() < 8) {
            throw new Exception("La contraseña debe tener al menos 8 caracteres");
        }
    }

    public Optional<Administrator> findAdministrator(long id) {
        return administratorRepository.findById(id);
    }

    public Administrator getAdministratorById(long id) {
        return administratorRepository.findById(id).orElse(null);
    }

    public Administrator findAdministratorByNameAndPassword(String name, String password) throws Exception {
        Optional<Administrator> administrator = administratorRepository.findByName(name);
        if (administrator.isPresent() && administrator.get().getPassword().equals(password)) {
            return administrator.get();
        } else {
            throw new Exception("Nombre o contraseña incorrectos");
        }
    }
}

