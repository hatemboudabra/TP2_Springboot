package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.EntrepriseRequest;

import java.util.List;

public interface EntrepriseService {
    EntrepriseRequest save(EntrepriseRequest entrepriseRequest);

    EntrepriseRequest findById(Integer id);

    List<EntrepriseRequest> findAll();

    void delete(Integer id);


}
