package net.javaguides.springboot.springbootbackend.controller.api;
import static net.javaguides.springboot.springbootbackend.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;
import static net.javaguides.springboot.springbootbackend.utils.Constants.CREATE_COMMANDE_FOURNISSEUR_ENDPOINT;
import static net.javaguides.springboot.springbootbackend.utils.Constants.FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT;
import static net.javaguides.springboot.springbootbackend.utils.Constants.FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT;
import static net.javaguides.springboot.springbootbackend.utils.Constants.FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT;
import static net.javaguides.springboot.springbootbackend.utils.Constants.DELETE_COMMANDE_FOURNISSEUR_ENDPOINT;
import io.swagger.annotations.Api;
import net.javaguides.springboot.springbootbackend.Ennumeration.EtatCommande;
import net.javaguides.springboot.springbootbackend.Request.CommandeFournisseurRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeFournisseurRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api("CommandeFournisseurApi")
public interface CommandeFournisseurApi {
    @PostMapping(CREATE_COMMANDE_FOURNISSEUR_ENDPOINT)
    CommandeFournisseurRequest save(@RequestBody CommandeFournisseurRequest commandeFournisseurRequest);

    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}")
    CommandeFournisseurRequest updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
    CommandeFournisseurRequest updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
                                                  @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/fournisseur/{idCommande}/{idFournisseur}")
    CommandeFournisseurRequest updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);

    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
    CommandeFournisseurRequest updateArticle(@PathVariable("idCommande") Integer idCommande,
                                         @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/article/{idCommande}/{idLigneCommande}")
    CommandeFournisseurRequest deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT)
    CommandeFournisseurRequest findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT)
    CommandeFournisseurRequest findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT)
    List<CommandeFournisseurRequest> findAll();

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}")
    List<LigneCommandeFournisseurRequest> findAllLignesCommandesFournisseurByCommandeFournisseurId(@PathVariable("idCommande") Integer idCommande);

    @DeleteMapping(DELETE_COMMANDE_FOURNISSEUR_ENDPOINT)
    void delete(@PathVariable("idCommandeFournisseur") Integer id);

}
