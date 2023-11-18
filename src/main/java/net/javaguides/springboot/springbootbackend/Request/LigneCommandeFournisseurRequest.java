package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.CommandeFournisseur;
import net.javaguides.springboot.springbootbackend.Model.LigneCommandeFournisseur;

import java.math.BigDecimal;

@Data
@Builder
public class LigneCommandeFournisseurRequest {
    private Integer id;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;

    private ArticleRequest article;

    private CommandeFournisseur commandeFournisseur;
    public static LigneCommandeFournisseurRequest fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            return null;
        }
        return LigneCommandeFournisseurRequest.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleRequest.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurRequest request) {
        if (request == null) {
            return null;
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(request.getId());
        ligneCommandeFournisseur.setArticle(ArticleRequest.toEntity(request.getArticle()));
        ligneCommandeFournisseur.setPrixUnitaire(request.getPrixUnitaire());
        ligneCommandeFournisseur.setQuantite(request.getQuantite());
        ligneCommandeFournisseur.setIdEntreprise(request.getIdEntreprise());
        return ligneCommandeFournisseur;
    }


}
