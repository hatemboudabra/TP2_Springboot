package net.javaguides.springboot.springbootbackend.Request;

import lombok.Builder;
import lombok.Data;
import net.javaguides.springboot.springbootbackend.Model.Article;

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
    public static ArticleRequest fromEntity(Article article) {
        if (article == null) {
            return null;
        }
        return ArticleRequest.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .photo(article.getPhoto())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .tauxTva(article.getTauxTva())
                .idEntreprise(article.getIdEntreprise())
                .category(CategoryRequest.fromEntity(article.getCategory()))
                .build();
    }

    public static Article toEntity(ArticleRequest articleRequest) {
        if (articleRequest == null) {
            return null;
        }
        Article article = new Article();
        article.setId(articleRequest.getId());
        article.setCodeArticle(articleRequest.getCodeArticle());
        article.setDesignation(articleRequest.getDesignation());
        article.setPhoto(articleRequest.getPhoto());
        article.setPrixUnitaireHt(articleRequest.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleRequest.getPrixUnitaireTtc());
        article.setTauxTva(articleRequest.getTauxTva());
        article.setIdEntreprise(articleRequest.getIdEntreprise());
        article.setCategory(CategoryRequest.toEntity(articleRequest.getCategory()));
        return article;
    }


}
