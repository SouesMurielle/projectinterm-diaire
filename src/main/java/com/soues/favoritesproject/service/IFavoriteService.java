package com.soues.favoritesproject.service;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.persistence.entity.Favorite;

import java.util.List;

public interface IFavoriteService {

    List<Favorite> findAll();

//    FavoriteItem findOne(long id);

    List<Favorite> findByCategory(Long id);

    FavoriteItem save(FavoriteDefinition favorite, Long categoryId);

    void delete(List<Long> listId);

}
