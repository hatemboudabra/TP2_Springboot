package net.javaguides.springboot.springbootbackend.validator;

import net.javaguides.springboot.springbootbackend.Request.CategoryRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {
    public static List<String> validate(CategoryRequest categoryRequest) {
        List<String> errors = new ArrayList<>();

        if (categoryRequest == null || !StringUtils.hasLength(categoryRequest.getCode())) {
            errors.add("Veuillez renseigner le code de la categorie");
        }
        return errors;
    }
}
