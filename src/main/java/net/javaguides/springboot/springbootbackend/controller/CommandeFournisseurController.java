package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
import net.javaguides.springboot.springbootbackend.Request.CommandeFournisseurRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeFournisseurRequest;
import net.javaguides.springboot.springbootbackend.Services.CommandeFournisseurService;
import net.javaguides.springboot.springbootbackend.controller.api.CommandeFournisseurApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {
    CommandeFournisseurService commandeFournisseurService;
    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }


    @Override
    public CommandeFournisseurRequest save(CommandeFournisseurRequest commandeFournisseurRequest) {
        return commandeFournisseurService.save(commandeFournisseurRequest);
    }

    @Override
    public CommandeFournisseurRequest updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return commandeFournisseurService.updateEtatCommande(idCommande, etatCommande);
    }

    @Override
    public CommandeFournisseurRequest updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite);
    }

    @Override
    public CommandeFournisseurRequest updateFournisseur(Integer idCommande, Integer idFournisseur) {
        return commandeFournisseurService.updateFournisseur(idCommande, idFournisseur);
    }

    @Override
    public CommandeFournisseurRequest updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle);
    }

    @Override
    public CommandeFournisseurRequest deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return commandeFournisseurService.deleteArticle(idCommande, idLigneCommande);
    }

    @Override
    public CommandeFournisseurRequest findById(Integer id) {
        return commandeFournisseurService.findById(id);
    }

    @Override
    public CommandeFournisseurRequest findByCode(String code) {
        return commandeFournisseurService.findByCode(code);
    }

    @Override
    public List<CommandeFournisseurRequest> findAll() {
        return commandeFournisseurService.findAll();
    }

    @Override
    public List<LigneCommandeFournisseurRequest> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande) {
        return commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande);
    }

    @Override
    public void delete(Integer id) {
        commandeFournisseurService.delete(id);
    }
}
