package com.soues.favoritesproject.controller;

import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.persistence.repository.IFavoriteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/favorite")
public class FavoriteController {

    private final IFavoriteRepository favoriteRepository;

    public FavoriteController(IFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }


    @GetMapping
    List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }
}
