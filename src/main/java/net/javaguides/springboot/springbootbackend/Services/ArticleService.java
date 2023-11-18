package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.ArticleRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeClientRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeFournisseurRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneVenteRequest;

import java.util.List;

public interface ArticleService {

    ArticleRequest save(ArticleRequest articleRequest);

    ArticleRequest findById(Integer id);

    ArticleRequest findByCodeArticle(String codeArticle);

    List<ArticleRequest> findAll();

    List<LigneVenteRequest> findHistoriqueVentes(Integer idArticle);

    List<LigneCommandeClientRequest> findHistoriaueCommandeClient(Integer idArticle);

    List<LigneCommandeFournisseurRequest> findHistoriqueCommandeFournisseur(Integer idArticle);

    List<ArticleRequest> findAllArticleByIdCategory(Integer idCategory);

    void delete(Integer id);


}
