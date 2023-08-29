package com.soues.favoritesproject.controller;

import com.soues.favoritesproject.dto.CategoryDefinition;
import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.dto.CategoryListItem;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.service.ICategoryService;
import com.soues.favoritesproject.service.IFavoriteService;
import com.soues.favoritesproject.utils.DTOHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CategoriesController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IFavoriteService favoriteService;

    @GetMapping(path = "/category")
    List<CategoryListItem> findAll() {
        return categoryService.findAll()
                ;
    }

    @GetMapping(path = "/{category}")
    List<FavoriteItem> findByCategory(@PathVariable(name = "category") long id) {
        return favoriteService.findByCategory(id)
                .stream()
                .map(favorite -> new FavoriteItem(favorite.getId(),
                        favorite.getLabel(), favorite.getLink(), favorite.getDate(), favorite.getCategory()))
                .toList();
    }

    @PostMapping
    CategoryItem save(@RequestBody CategoryDefinition category) {
        return categoryService.save(category);
    }


}
