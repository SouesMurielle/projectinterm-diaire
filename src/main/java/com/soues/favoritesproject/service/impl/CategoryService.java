package com.soues.favoritesproject.service.impl;

import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.persistence.entity.Category;
import com.soues.favoritesproject.persistence.repository.ICategoryRepository;
import com.soues.favoritesproject.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

//    @Override
//    public CategoryItem findOne(Long id) {
//        return null;
//    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CategoryItem save(CategoryItem category) {
        Category entity = categoryRepository.save(new Category(category.getId(), category.getLabel()));
        return new CategoryItem(entity.getId(), entity.getLabel());
    }
}
// @Transactional indique que toutes les methodes que le service contient deviennent transactionnelles.
// cad historiser toutes les opérations/instructions faites depuis le début de la transaction
// et être capable de faire marche arrière
