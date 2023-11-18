package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.LigneCommandeClient;

import java.math.BigDecimal;

@Builder
@Data
public class LigneCommandeClientRequest {
    private Integer id;
    private ArticleRequest article;
    @JsonIgnore
    private CommandeClientRequest commandeClient;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private Integer idEntreprise;
    public static LigneCommandeClientRequest fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (ligneCommandeClient == null) {
            return null;
        }
        return LigneCommandeClientRequest.builder()
                .id(ligneCommandeClient.getId())
                .article(ArticleRequest.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .idEntreprise(ligneCommandeClient.getIdEntreprise())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientRequest ligneCommandeClientRequest) {
        if (ligneCommandeClientRequest == null) {
            return null;
        }

        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientRequest.getId());
        ligneCommandeClient.setArticle(ArticleRequest.toEntity(ligneCommandeClientRequest.getArticle()));
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientRequest.getPrixUnitaire());
        ligneCommandeClient.setQuantite(ligneCommandeClientRequest.getQuantite());
        ligneCommandeClient.setIdEntreprise(ligneCommandeClientRequest.getIdEntreprise());
        return ligneCommandeClient;
    }
}
