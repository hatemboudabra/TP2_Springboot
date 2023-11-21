package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.ChangerMotDePasseUtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Services.UtilisateurService;
import net.javaguides.springboot.springbootbackend.controller.api.UtilisateurApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UtilisateurController implements UtilisateurApi {
    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurRequest save(UtilisateurRequest utilisateurRequest) {
        return utilisateurService.save(utilisateurRequest);
    }

    @Override
    public UtilisateurRequest changerMotDePasse(ChangerMotDePasseUtilisateurRequest changerMotDePasseUtilisateurRequest) {
        return utilisateurService.changerMotDePasse(changerMotDePasseUtilisateurRequest);
    }

    @Override
    public UtilisateurRequest findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurRequest findByEmail(String email) {
        return utilisateurService.findByEmail(email);
    }

    @Override
    public List<UtilisateurRequest> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
