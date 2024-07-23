package uce.edu.ec.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uce.edu.ec.model.Administrator;
import uce.edu.ec.service.AdministratorService;
import uce.edu.ec.repository.AdministratorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Administrator saveAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator getAdministratorById(Long id) {
        Optional<Administrator> administrator = administratorRepository.findById(id);
        return administrator.orElse(null);
    }

    @Override
    public List<Administrator> getAllAdministrator() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator updateAdministrator(Long id, Administrator administrator) {
        if (administratorRepository.existsById(id)) {
            administrator.setId(id);
            return administratorRepository.save(administrator);
        }
        return null;
    }

    @Override
    public void deleteAdministrator(Long id) {
        administratorRepository.deleteById(id);
    }
}
