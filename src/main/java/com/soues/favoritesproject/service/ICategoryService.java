package com.soues.favoritesproject.service;

import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.persistence.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();

//    CategoryItem findOne(Long id);

    void delete(Long id);

    CategoryItem save(CategoryItem category);
}