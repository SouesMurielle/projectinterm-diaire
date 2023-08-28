package com.soues.favoritesproject.controller;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/favorite")
public class FavoriteController {

    @Autowired
    private IFavoriteService favoriteService;

    @GetMapping
    List<FavoriteItem> findAll() {
        return favoriteService.findAll()
                .stream()
                .map(favorite -> new FavoriteItem(favorite.getId(), favorite.getLink(), favorite.getLabel(), favorite.getDate(), favorite.getCategory()))
                .toList();
    }

//    @GetMapping(path = "/{id}")
//    FavoriteItem findOne(@PathVariable long id) {
//        return favoriteService.findOne(id);
//    }

    @PostMapping(path = "/{categoryId}/favorite")
    FavoriteItem save(@RequestBody FavoriteDefinition favorite,@PathVariable(name = "categoryId") Long categoryId) {
        return favoriteService.save(favorite, categoryId);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void delete(@RequestParam List<Long> listId) {
        favoriteService.delete(listId);
    }

}
