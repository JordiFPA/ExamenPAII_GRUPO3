package uce.edu.ec.service;

import uce.edu.ec.model.Administrator;

import java.util.List;

public interface AdministratorService {

    Administrator saveAdministrator(Administrator administrator);

    Administrator getAdministratorById(Long id);

    List<Administrator> getAllAdministrator();

    Administrator updateAdministrator(Long id, Administrator administrator);

    void deleteAdministrator(Long id);
}

