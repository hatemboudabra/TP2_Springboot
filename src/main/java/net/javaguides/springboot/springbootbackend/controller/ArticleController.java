package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.ArticleRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeClientRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeFournisseurRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneVenteRequest;
import net.javaguides.springboot.springbootbackend.Services.ArticleService;
import net.javaguides.springboot.springbootbackend.controller.api.ArticleApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ArticleController implements ArticleApi {
    private ArticleService articleService;

    @Autowired
    public ArticleController(
            ArticleService articleService
    ) {
        this.articleService = articleService;
    }
    @Override
    public ArticleRequest save(ArticleRequest articleRequest) {
        return articleService.save(articleRequest);
    }

    @Override
    public ArticleRequest findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleRequest findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleRequest> findAll() {
        return articleService.findAll();
    }

    @Override
    public List<LigneVenteRequest> findHistoriqueVentes(Integer idArticle) {
        return articleService.findHistoriqueVentes(idArticle);
    }

    @Override
    public List<LigneCommandeClientRequest> findHistoriaueCommandeClient(Integer idArticle) {
        return articleService.findHistoriaueCommandeClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurRequest> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandeFournisseur(idArticle);
    }

    @Override
    public List<ArticleRequest> findAllArticleByIdCategory(Integer idCategory) {
        return articleService.findAllArticleByIdCategory(idCategory);
    }

    @Override
    public void delete(Integer id) {
            articleService.delete(id);
    }
}
