package net.javaguides.springboot.springbootbackend.validator;

import net.javaguides.springboot.springbootbackend.Request.ArticleRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {
    public static List<String> validate(ArticleRequest articleRequest) {
        List<String> errors = new ArrayList<>();

        if (articleRequest == null) {
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner la designation de l'article");
            errors.add("Veuillez renseigner le prix unitaire HT l'article");
            errors.add("Veuillez renseigner le taux TVA de l'article");
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
            errors.add("Veuillez selectionner une categorie");
            return errors;
        }

        if (!StringUtils.hasLength(articleRequest.getCodeArticle())) {
            errors.add("Veuillez renseigner le code de l'article");
        }
        if (!StringUtils.hasLength(articleRequest.getDesignation())) {
            errors.add("Veuillez renseigner la designation de l'article");
        }
        if (articleRequest.getPrixUnitaireHt() == null) {
            errors.add("Veuillez renseigner le prix unitaire HT l'article");
        }
        if (articleRequest.getTauxTva() == null) {
            errors.add("Veuillez renseigner le taux TVA de l'article");
        }
        if (articleRequest.getPrixUnitaireTtc() == null) {
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
        }
        if (articleRequest.getCategory() == null || articleRequest.getCategory().getId() == null) {
            errors.add("Veuillez selectionner une categorie");
        }
        return errors;
    }
}
