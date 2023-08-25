package com.soues.favoritesproject.controller;

import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.persistence.entity.Category;
import com.soues.favoritesproject.service.ICategoryService;
import com.soues.favoritesproject.service.IFavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {

    private final ICategoryService categoryService;

    private final IFavoriteService favoriteService;

    public CategoryController(ICategoryService categoryService, IFavoriteService favoriteService) {
        this.categoryService = categoryService;
        this.favoriteService = favoriteService;
    }

    @GetMapping
    List<CategoryItem> findAll() {
        return categoryService.findAll()
                .stream()
                .map(category -> new CategoryItem(category.getId(), category.getLabel()))
                .toList();
    }

    @GetMapping(path = "/{category}")
    List<FavoriteItem> findByCategory(@PathVariable(name = "category") long id) {
        return favoriteService.findByCategory(id)
                .stream()
                .map(favorite -> new FavoriteItem(favorite.getId(),
                        favorite.getLabel(), favorite.getLink(), favorite.getDate(), favorite.getCategory()))
                .toList();
//        return favoriteService.findByCategory(id)
//                .stream()
//                .map(favorite -> new FavoriteItem(favorite.getId(),
//                favorite.getLabel(), favorite.getLink(), favorite.getDate(), favorite.getCategory()))
//                .toList();
    }

    @PostMapping
    CategoryItem save(@RequestBody CategoryItem category) {
        return categoryService.save(category);
    }


}
