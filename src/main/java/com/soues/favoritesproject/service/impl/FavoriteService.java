package com.soues.favoritesproject.service.impl;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.exception.NotFoundException;
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
//    utile si demande de modifier ? autre ?
//    public FavoriteItem findOne(long id) {
//        Favorite favorite = favoriteRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Pas trouvé"));
//        return new FavoriteItem(favorite.getId(), favorite.getCategory(), favorite.getLabel(), favorite.getLink(), favorite.getDate());
//    }

    @Override
    public FavoriteItem save(FavoriteDefinition favorite) {
        Favorite entity = favoriteRepository.save(new Favorite(favorite.getId(), favorite.getCategory(), favorite.getLabel(), favorite.getLink(),null));
        return new FavoriteItem(entity.getId(), entity.getCategory(), entity.getLabel(), entity.getLink(), entity.getDate());
    }

    @Override
    public void delete(long id) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new NotFoundException("Pas trouvé"));
        favoriteRepository.delete(favorite);
    }


}
