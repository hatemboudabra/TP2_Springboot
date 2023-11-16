package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;


@Builder
@Data
public class ArticleRequest {
    private Integer id;
    private String codeArticle;
    private String designation;
    private BigDecimal prixUnitaireHt;
    private BigDecimal tauxTva;
    private BigDecimal prixUnitaireTtc;
    private String photo;
    private Integer idEntreprise;

    private CategoryRequest  category;

}
