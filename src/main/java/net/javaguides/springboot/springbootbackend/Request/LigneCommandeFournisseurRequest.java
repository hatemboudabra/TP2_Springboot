package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.CommandeFournisseur;

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

}
