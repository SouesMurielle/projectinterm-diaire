package com.soues.favoritesproject.controller;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.service.IFavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/favorite")
public class FavoriteController {

    private   final IFavoriteService favoriteService;

    public FavoriteController(IFavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }


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



//    @GetMapping(path = "/{category}")
//    List<FavoriteItem> findByCategory(String category) {
//        return favoriteService.findByCategory(category)
//                .stream()
//                .map(favorite -> new FavoriteItem(favorite.getId(), favorite.getCategory(), favorite.getLabel(), favorite.getLink(), favorite.getDate()))
//                .toList();
//    }

    @PostMapping
    FavoriteItem save(@RequestBody FavoriteDefinition favorite) {
        return favoriteService.save(favorite);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void delete(@RequestParam List<Long> listId) {
        favoriteService.delete(listId);
    }

}
