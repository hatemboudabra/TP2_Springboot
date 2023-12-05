package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.FournisseurRequest;
import net.javaguides.springboot.springbootbackend.Services.FournisseurService;
import net.javaguides.springboot.springbootbackend.controller.api.FournisseurApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FournisseurController implements FournisseurApi {

    FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }
    @Override
    public FournisseurRequest save(FournisseurRequest fournisseurRequest) {
        return fournisseurService.save(fournisseurRequest);
    }

    @Override
    public FournisseurRequest findById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurRequest> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        fournisseurService.delete(id);
    }
}
