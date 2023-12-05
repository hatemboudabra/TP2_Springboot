package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
import net.javaguides.springboot.springbootbackend.Request.CommandeClientRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeClientRequest;
import net.javaguides.springboot.springbootbackend.Services.CommandeClientService;
import net.javaguides.springboot.springbootbackend.controller.api.CommandeClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
@RestController
public class CommandeClientController implements CommandeClientApi {
   private CommandeClientService commandeClientService;
    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }
    @Override
    public ResponseEntity<CommandeClientRequest> save(CommandeClientRequest commandeClientRequest) {
        return ResponseEntity.ok(commandeClientService.save(commandeClientRequest));
    }

    @Override
    public ResponseEntity<CommandeClientRequest> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeClientService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeClientRequest> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(commandeClientService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeClientRequest> updateClient(Integer idCommande, Integer idClient) {
        return ResponseEntity.ok(commandeClientService.updateClient(idCommande, idClient));
    }

    @Override
    public ResponseEntity<CommandeClientRequest> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeClientRequest> deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(commandeClientService.deleteArticle(idCommande, idLigneCommande));
    }

    @Override
    public ResponseEntity<CommandeClientRequest> findById(Integer idCommandeClient) {
        return ResponseEntity.ok(commandeClientService.findById(idCommandeClient));
    }

    @Override
    public ResponseEntity<CommandeClientRequest> findByCode(String code) {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientRequest>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCommandeClientRequest>> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
        return ResponseEntity.ok(commandeClientService.findAllLignesCommandesClientByCommandeClientId(idCommande));
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
