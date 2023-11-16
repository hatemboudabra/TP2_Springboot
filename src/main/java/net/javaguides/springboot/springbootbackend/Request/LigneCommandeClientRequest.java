package net.javaguides.springboot.springbootbackend.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
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
}
