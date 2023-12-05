package net.javaguides.springboot.springbootbackend.Services.imp;

import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.springbootbackend.Ennumeration.TypeMvtStk;
import net.javaguides.springboot.springbootbackend.Repository.MvtStkRepository;
import net.javaguides.springboot.springbootbackend.Request.MvtStkRequest;
import net.javaguides.springboot.springbootbackend.Services.ArticleService;
import net.javaguides.springboot.springbootbackend.Services.MvtStkService;
import net.javaguides.springboot.springbootbackend.exception.ErrorCodes;
import net.javaguides.springboot.springbootbackend.exception.InvalidEntityException;
import net.javaguides.springboot.springbootbackend.validator.MvtStkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MvtStkServiceImpl implements MvtStkService {
    private MvtStkRepository mvtStkRepository;
    private ArticleService articleService;

    @Autowired
    public MvtStkServiceImpl(MvtStkRepository repository, ArticleService articleService) {
        this.mvtStkRepository = mvtStkRepository;
        this.articleService = articleService;
    }
    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null) {
            log.warn("ID article is NULL");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvtStkRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkRequest> mvtStkArticle(Integer idArticle) {
        return mvtStkRepository.findAllByArticleId(idArticle).stream()
                .map(MvtStkRequest::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStkRequest entreeStock(MvtStkRequest mvtStkRequest) {
        return entreePositive(mvtStkRequest, TypeMvtStk.ENTREE);
    }

    @Override
    public MvtStkRequest sortieStock(MvtStkRequest mvtStkRequest) {
        return sortieNegative(mvtStkRequest, TypeMvtStk.CORRECTION_NEG);    }

    @Override
    public MvtStkRequest correctionStockPos(MvtStkRequest mvtStkRequest) {
        return entreePositive(mvtStkRequest, TypeMvtStk.CORRECTION_POS);
    }

    @Override
    public MvtStkRequest correctionStockNeg(MvtStkRequest mvtStkRequest) {
        return sortieNegative(mvtStkRequest, TypeMvtStk.CORRECTION_POS);
    }
    private MvtStkRequest entreePositive(MvtStkRequest mvtStkRequest, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(mvtStkRequest);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", mvtStkRequest);
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        mvtStkRequest.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(mvtStkRequest.getQuantite().doubleValue())
                )
        );
        mvtStkRequest.setTypeMvt(typeMvtStk);
        return MvtStkRequest.fromEntity(
                mvtStkRepository.save(MvtStkRequest.toEntity(mvtStkRequest))
        );
    }
    private MvtStkRequest sortieNegative(MvtStkRequest mvtStkRequest, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(mvtStkRequest);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", mvtStkRequest);
            throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STK_NOT_VALID, errors);
        }
        mvtStkRequest.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(mvtStkRequest.getQuantite().doubleValue()) * -1
                )
        );
        mvtStkRequest.setTypeMvt(typeMvtStk);
        return MvtStkRequest.fromEntity(
                mvtStkRepository.save(MvtStkRequest.toEntity(mvtStkRequest))
        );
    }
}
