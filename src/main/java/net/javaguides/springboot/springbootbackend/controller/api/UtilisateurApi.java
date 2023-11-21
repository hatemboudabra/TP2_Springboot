package net.javaguides.springboot.springbootbackend.controller.api;

import io.swagger.annotations.Api;
import net.javaguides.springboot.springbootbackend.Request.ChangerMotDePasseUtilisateurRequest;
import net.javaguides.springboot.springbootbackend.Request.UtilisateurRequest;
import org.springframework.web.bind.annotation.*;

import static net.javaguides.springboot.springbootbackend.utils.Constants.UTILISATEUR_ENDPOINT;
import java.util.List;

@Api("utilisateurs")
public interface UtilisateurApi {
    @PostMapping(UTILISATEUR_ENDPOINT + "/create")
    UtilisateurRequest save(@RequestBody UtilisateurRequest utilisateurRequest);

    @PostMapping(UTILISATEUR_ENDPOINT + "/update/password")
    UtilisateurRequest changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurRequest changerMotDePasseUtilisateurRequest);

    @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
    UtilisateurRequest findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(UTILISATEUR_ENDPOINT + "/find/{email}")
    UtilisateurRequest findByEmail(@PathVariable("email") String email);

    @GetMapping(UTILISATEUR_ENDPOINT + "/all")
    List<UtilisateurRequest> findAll();

    @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);



}
