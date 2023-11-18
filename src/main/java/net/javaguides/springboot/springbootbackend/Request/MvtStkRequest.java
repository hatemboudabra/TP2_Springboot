package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Ennumeration.SourceMvtStk;
import net.javaguides.springboot.springbootbackend.Ennumeration.TypeMvtStk;
import net.javaguides.springboot.springbootbackend.Model.MvtStk;

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
    public static MvtStkRequest fromEntity(MvtStk mvtStk) {
        if (mvtStk == null) {
            return null;
        }

        return MvtStkRequest.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .article(ArticleRequest.fromEntity(mvtStk.getArticle()))
                .typeMvt(mvtStk.getTypeMvt())
                .sourceMvt(mvtStk.getSourceMvt())
                .idEntreprise(mvtStk.getIdEntreprise())
                .build();
    }

    public static MvtStk toEntity(MvtStkRequest mvtStkRequest) {
        if (mvtStkRequest == null) {
            return null;
        }

        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkRequest.getId());
        mvtStk.setDateMvt(mvtStkRequest.getDateMvt());
        mvtStk.setQuantite(mvtStkRequest.getQuantite());
        mvtStk.setArticle(ArticleRequest.toEntity(mvtStkRequest.getArticle()));
        mvtStk.setTypeMvt(mvtStkRequest.getTypeMvt());
        mvtStk.setSourceMvt(mvtStkRequest.getSourceMvt());
        mvtStk.setIdEntreprise(mvtStkRequest.getIdEntreprise());
        return mvtStk;
    }
}
