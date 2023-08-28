package com.soues.favoritesproject.service;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;

import java.util.List;

public interface IFavoriteService {

    List<FavoriteItem> findAll();

//    FavoriteItem findOne(long id);

    List<FavoriteItem> findByCategory(long id);

    FavoriteItem save(FavoriteDefinition favorite, Long categoryId);

    void delete(long id);

    void deleteMultiple(List<Long> ids);

}
