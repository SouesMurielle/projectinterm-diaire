package com.soues.favoritesproject.service.impl;

import com.soues.favoritesproject.dto.CategoryDefinition;
import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.persistence.entity.Category;
import com.soues.favoritesproject.persistence.repository.ICategoryRepository;
import com.soues.favoritesproject.service.ICategoryService;
import com.soues.favoritesproject.utils.DTOHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    private DTOHelper helper;

    @Override
    public List<CategoryItem> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> helper.toCategoryItem(category))
                .toList();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public CategoryItem save(CategoryDefinition category) {
        Category entity = categoryRepository.save(new Category(category.getId(), category.getLabel()));
        return helper.toCategoryItem(entity);
    }
}
// @Transactional indique que toutes les methodes que le service contient deviennent transactionnelles.
// cad historiser toutes les opérations/instructions faites depuis le début de la transaction
// et être capable de faire marche arrière
