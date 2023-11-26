package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.FournisseurRequest;

import java.util.List;

public interface FournisseurService {
    FournisseurRequest save(FournisseurRequest fournisseurRequest);

    FournisseurRequest findById(Integer id);

    List<FournisseurRequest> findAll();

    void delete(Integer id);

}
