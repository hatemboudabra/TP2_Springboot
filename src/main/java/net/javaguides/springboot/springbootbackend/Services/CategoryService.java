package net.javaguides.springboot.springbootbackend.Services;

import net.javaguides.springboot.springbootbackend.Request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    CategoryRequest save(CategoryRequest categoryRequest);
    CategoryRequest findById( Integer id);
    CategoryRequest findByCode(String code);
    List<CategoryRequest> findAll();

    void delete (Integer id);

}
