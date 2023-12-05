package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Ennumeration.SourceMvtStk;
import net.javaguides.springboot.springbootbackend.Ennumeration.TypeMvtStk;
import net.javaguides.springboot.springbootbackend.Model.Article;
import net.javaguides.springboot.springbootbackend.Model.LigneVente;
import net.javaguides.springboot.springbootbackend.Model.Ventes;
import net.javaguides.springboot.springbootbackend.Repository.ArticleRepository;
import net.javaguides.springboot.springbootbackend.Repository.LigneVenteRepository;
import net.javaguides.springboot.springbootbackend.Repository.VentesRepository;
import net.javaguides.springboot.springbootbackend.Request.ArticleRequest;
import net.javaguides.springboot.springbootbackend.Request.LigneVenteRequest;
import net.javaguides.springboot.springbootbackend.Request.MvtStkRequest;
import net.javaguides.springboot.springbootbackend.Request.VentesRequest;
import net.javaguides.springboot.springbootbackend.Services.MvtStkService;
import net.javaguides.springboot.springbootbackend.Services.VentesService;
import net.javaguides.springboot.springbootbackend.exception.EntityNotFoundException;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.exception.InvalidOperationException;
import net.javaguides.springboot.springbootbackend.validator.VentesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {
     private ArticleRepository articleRepository;
     private VentesRepository ventesRepository;
     private LigneVenteRepository ligneVenteRepository;
     private MvtStkService mvtStkService;
    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository,
                             LigneVenteRepository ligneVenteRepository, MvtStkService mvtStkService) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStkService = mvtStkService;
    }
    @Override
    public VentesRequest save(VentesRequest ventesRequest) {
        List<String> errors = VentesValidator.validate(ventesRequest);
        if (!errors.isEmpty()) {
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        ventesRequest.getLigneVentes().forEach(ligneVenteRequest -> {
            Optional<Article> article = articleRepository.findById(ligneVenteRequest.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID " + ligneVenteRequest.getArticle().getId() + " n'a ete trouve dans la BDD");
            }
        });

        if (!articleErrors.isEmpty()) {
            log.error("One or more articles were not found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Ventes savedVentes = ventesRepository.save(VentesRequest.toEntity(ventesRequest));

        ventesRequest.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteRequest.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVentes);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);
        });

        return VentesRequest.fromEntity(savedVentes);

    }

    @Override
    public VentesRequest findById(Integer id) {
        if (id == null) {
            log.error("Ventes ID is NULL");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesRequest::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun vente n'a ete trouve dans la BDD", ErrorCodes.VENTE_NOT_FOUND));

    }

    @Override
    public VentesRequest findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Vente CODE is NULL");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesRequest::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente client n'a ete trouve avec le CODE " + code, ErrorCodes.VENTE_NOT_VALID
                ));
    }

    @Override
    public List<VentesRequest> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Vente ID is NULL");
            return;
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVenteId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une vente ...",
                    ErrorCodes.VENTE_ALREADY_IN_USE);
        }
        ventesRepository.deleteById(id);
    }
    private void updateMvtStk(LigneVente lig) {
        MvtStkRequest mvtStkDto = MvtStkRequest.builder()
                .article(ArticleRequest.fromEntity(lig.getArticle()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStk.SORTIE)
                .sourceMvt(SourceMvtStk.VENTE)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
        mvtStkService.sortieStock(mvtStkDto);
    }
}
