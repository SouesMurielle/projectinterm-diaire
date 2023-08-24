package com.soues.favoritesproject.service.impl;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.persistence.repository.IFavoriteRepository;
import com.soues.favoritesproject.service.IFavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService implements IFavoriteService  {

    private final IFavoriteRepository favoriteRepository;

    public FavoriteService(IFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }


    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public FavoriteItem save(FavoriteDefinition favorite) {
        Favorite entity = favoriteRepository.save(new Favorite(favorite.getId(), favorite.getCategory(), favorite.getLabel(), favorite.getLink(),null));
        return new FavoriteItem(entity.getId(), entity.getCategory(), entity.getLabel(), entity.getLink(), entity.getDate());
    }


}
