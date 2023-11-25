package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
import net.javaguides.springboot.springbootbackend.Request.CommandeClientRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeClientRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {
    CommandeClientRequest save(CommandeClientRequest commandeClientRequest);

    CommandeClientRequest updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeClientRequest updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientRequest updateClient(Integer idCommande, Integer idClient);

    CommandeClientRequest updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

    // Delete article ==> delete LigneCommandeClient
    CommandeClientRequest deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeClientRequest findById(Integer id);

    CommandeClientRequest findByCode(String code);

    List<CommandeClientRequest> findAll();

    List<LigneCommandeClientRequest> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);
    void delete(Integer id);

}
