package com.soues.favoritesproject.service;

import com.soues.favoritesproject.dto.CategoryDefinition;
import com.soues.favoritesproject.dto.CategoryItem;

import java.util.List;

public interface ICategoryService {

    List<CategoryItem> findAll();

    void delete(long id);

    CategoryItem save(CategoryDefinition category);
}
