package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Ennumeration.SourceMvtStk;
import net.javaguides.springboot.springbootbackend.Ennumeration.TypeMvtStk;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkRequest {
    private Integer id;
    private Instant dateMvt;
    private BigDecimal quantite;
    private ArticleRequest article;
    private TypeMvtStk typeMvt;
    private SourceMvtStk sourceMvt;
    private Integer idEntreprise;
}
