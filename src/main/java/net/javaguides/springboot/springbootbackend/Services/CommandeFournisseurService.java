package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
import net.javaguides.springboot.springbootbackend.Repository.CommandeFournisseurRepository;
import net.javaguides.springboot.springbootbackend.Request.CommandeFournisseurRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeFournisseurRequest;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurRequest save(CommandeFournisseurRequest commandeFournisseurRequest);

    CommandeFournisseurRequest updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeFournisseurRequest updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeFournisseurRequest updateFournisseur(Integer idCommande, Integer idFournisseur);

    CommandeFournisseurRequest updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

    // Delete article ==> delete LigneCommandeFournisseur
    CommandeFournisseurRequest deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeFournisseurRequest findById(Integer id);

    CommandeFournisseurRequest findByCode(String code);

    List<CommandeFournisseurRequest> findAll();

    List<LigneCommandeFournisseurRequest> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

    void delete(Integer id);
}
