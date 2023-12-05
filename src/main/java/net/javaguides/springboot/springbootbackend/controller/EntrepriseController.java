package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.EntrepriseRequest;
import net.javaguides.springboot.springbootbackend.Services.EntrepriseService;
import net.javaguides.springboot.springbootbackend.controller.api.EntrepriseApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class EntrepriseController implements EntrepriseApi {
    private EntrepriseService entrepriseService;
    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }
    @Override
    public EntrepriseRequest save(EntrepriseRequest entrepriseRequest) {
        return entrepriseService.save(entrepriseRequest);
    }

    @Override
    public EntrepriseRequest findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseRequest> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Integer id) {
        entrepriseService.delete(id);
    }
}
