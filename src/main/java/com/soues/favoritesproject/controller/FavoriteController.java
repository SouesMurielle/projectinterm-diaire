package com.soues.favoritesproject.controller;

import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.service.IFavoriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/favorite")
public class FavoriteController {

    private   final IFavoriteService favoriteService;

    public FavoriteController(IFavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }


    @GetMapping
    List<Favorite> findAll() {
        return favoriteService.findAll();
    }
}
