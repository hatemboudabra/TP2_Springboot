package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Model.LigneCommandeClient;
import net.javaguides.springboot.springbootbackend.Model.LigneCommandeFournisseur;
import net.javaguides.springboot.springbootbackend.Model.LigneVente;
import net.javaguides.springboot.springbootbackend.Repository.ArticleRepository;
import net.javaguides.springboot.springbootbackend.Repository.LigneCommandeClientRepository;
import net.javaguides.springboot.springbootbackend.Repository.LigneCommandeFournisseurRepository;
import net.javaguides.springboot.springbootbackend.Repository.LigneVenteRepository;
import net.javaguides.springboot.springbootbackend.Request.ArticleRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeClientRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneCommandeFournisseurRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneVenteRequest;
import net.javaguides.springboot.springbootbackend.Services.ArticleService;
import net.javaguides.springboot.springbootbackend.exception.EntityNotFoundException;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import net.javaguides.springboot.springbootbackend.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;
    private LigneVenteRepository venteRepository;
    private LigneCommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeClientRepository commandeClientRepository;
    @Autowired
    public ArticleServiceImpl(
            ArticleRepository articleRepository,
            LigneVenteRepository venteRepository, LigneCommandeFournisseurRepository commandeFournisseurRepository,
            LigneCommandeClientRepository commandeClientRepository) {
        this.articleRepository = articleRepository;
        this.venteRepository = venteRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.commandeClientRepository = commandeClientRepository;
    }
    @Override
    public ArticleRequest save(ArticleRequest articleRequest) {
        List<String> errors = ArticleValidator.validate(articleRequest);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", articleRequest);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleRequest.fromEntity(
                    articleRepository.save(
                            ArticleRequest.toEntity(articleRequest)
                    )
            );

    }

    @Override
    public ArticleRequest findById(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return null;
        }

        return articleRepository.findById(id).map(ArticleRequest::fromEntity).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec l'ID = " + id + " n' ete trouve dans la BDD",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleRequest findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)) {
            log.error("Article CODE is null");
            return null;
        }

        return articleRepository.findArticleByCodeArticle(codeArticle)
                .map(ArticleRequest::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Aucun article avec le CODE = " + codeArticle + " n' ete trouve dans la BDD",
                                ErrorCodes.ARTICLE_NOT_FOUND)
                );    }

    @Override
    public List<ArticleRequest> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneVenteRequest> findHistoriqueVentes(Integer idArticle) {
        return venteRepository.findAllByArticleId(idArticle).stream()
                .map(LigneVenteRequest::fromEntity)
                .collect(Collectors.toList());    }

    @Override
    public List<LigneCommandeClientRequest> findHistoriaueCommandeClient(Integer idArticle) {
        return commandeClientRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeClientRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurRequest> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return commandeFournisseurRepository.findAllByArticleId(idArticle).stream()
                .map(LigneCommandeFournisseurRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleRequest> findAllArticleByIdCategory(Integer idCategory) {
        return articleRepository.findAllByCategoryId(idCategory).stream()
                .map(ArticleRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Article ID is null");
            return;
        }
        List<LigneCommandeClient> ligneCommandeClients = commandeClientRepository.findAllByArticleId(id);
        if (!ligneCommandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes client", ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = commandeFournisseurRepository.findAllByArticleId(id);
        if (!ligneCommandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des commandes fournisseur",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }
        List<LigneVente> ligneVentes = venteRepository.findAllByArticleId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un article deja utilise dans des ventes",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }
        articleRepository.deleteById(id);
    }
    }
