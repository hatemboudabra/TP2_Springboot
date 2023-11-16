package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
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
}
