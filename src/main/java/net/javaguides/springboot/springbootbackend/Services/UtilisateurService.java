package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.ChangerMotDePasseUtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;

import java.util.List;

public interface UtilisateurService {
    UtilisateurRequest save(UtilisateurRequest utilisateurRequest);

    UtilisateurRequest findById(Integer id);

    List<UtilisateurRequest> findAll();

    void delete(Integer id);

    UtilisateurRequest findByEmail(String email);

    UtilisateurRequest changerMotDePasse(ChangerMotDePasseUtilisateurRequest changerMotDePasseUtilisateurRequest);

}
