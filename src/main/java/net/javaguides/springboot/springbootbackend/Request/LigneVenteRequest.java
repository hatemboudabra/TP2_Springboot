package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.LigneVente;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteRequest {
    private Integer id;
    private VentesRequest vente;
    private ArticleRequest article;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;
    public static LigneVenteRequest fromEntity(LigneVente ligneVente) {
        if (ligneVente == null) {
            return null;
        }

        return LigneVenteRequest.builder()
                .id(ligneVente.getId())
                .vente(VentesRequest.fromEntity(ligneVente.getVente()))
                .article(ArticleRequest.fromEntity(ligneVente.getArticle()))
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteRequest dto) {
        if (dto == null) {
            return null;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(dto.getId());
        ligneVente.setVente(VentesRequest.toEntity(dto.getVente()));
        ligneVente.setArticle(ArticleRequest.toEntity(dto.getArticle()));
        ligneVente.setQuantite(dto.getQuantite());
        ligneVente.setPrixUnitaire(dto.getPrixUnitaire());
        ligneVente.setIdEntreprise(dto.getIdEntreprise());
        return ligneVente;
    }

}
