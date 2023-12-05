package net.javaguides.springboot.springbootbackend.controller;

import net.javaguides.springboot.springbootbackend.Request.CategoryRequest;
import net.javaguides.springboot.springbootbackend.Services.CategoryService;
import net.javaguides.springboot.springbootbackend.controller.api.CategoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class CategoryController implements CategoryApi {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Override
    public CategoryRequest save(CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }

    @Override
    public CategoryRequest findById(Integer idCategory) {
        return categoryService.findById(idCategory);
    }

    @Override
    public CategoryRequest findByCode(String codeCategory) {
        return categoryService.findByCode(codeCategory);
    }

    @Override
    public List<CategoryRequest> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categoryService.delete(id);
    }
}
