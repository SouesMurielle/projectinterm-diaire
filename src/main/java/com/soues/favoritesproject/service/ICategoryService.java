package com.soues.favoritesproject.service;

import com.soues.favoritesproject.dto.CategoryDefinition;
import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.persistence.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

    void delete(long id);

    CategoryItem save(CategoryDefinition category);
}
