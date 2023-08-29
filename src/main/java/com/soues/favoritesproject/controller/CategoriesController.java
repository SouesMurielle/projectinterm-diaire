package com.soues.favoritesproject.controller;

import com.soues.favoritesproject.dto.CategoryDefinition;
import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.dto.CategoryListItem;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.service.ICategoryService;
import com.soues.favoritesproject.service.IFavoriteService;
import com.soues.favoritesproject.utils.DTOHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return categoryService.findAll();
    }

    @GetMapping(path = "/{category}")
    List<FavoriteItem> findByCategory(@PathVariable(name = "category") long id) {
        return favoriteService.findByCategory(id);
    }

    @PostMapping
    CategoryItem save(@RequestBody CategoryDefinition category) {
        return categoryService.save(category);
    }

    @Operation(summary = "Delete a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The category was deleted"),
            @ApiResponse(responseCode = "404", description = "The category was not found"),
            @ApiResponse(responseCode = "500", description = "The category could not be deleted")
    })
    @DeleteMapping(path = "/category/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }

}
