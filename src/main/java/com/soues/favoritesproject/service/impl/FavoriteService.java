package com.soues.favoritesproject.service.impl;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.exception.NotFoundException;
import com.soues.favoritesproject.persistence.entity.Category;
import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.persistence.repository.ICategoryRepository;
import com.soues.favoritesproject.persistence.repository.IFavoriteRepository;
import com.soues.favoritesproject.service.IFavoriteService;
import com.soues.favoritesproject.utils.DTOHelper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FavoriteService implements IFavoriteService  {

    @Autowired
    private IFavoriteRepository favoriteRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    private DTOHelper helper = new DTOHelper() ;

    @Override
    public List<FavoriteItem> findAll() {
        return favoriteRepository.findAll().stream()
                .map(favorite -> helper.toFavoriteItem(favorite))
                .toList();
    }
//    @Override
//    public List<FavoriteItem> findAll() {
//        return favoriteRepository.findAll().stream()
//                .map(favorite -> new FavoriteItem(favorite.getId(), favorite.getLabel(),favorite.getLink(), favorite.getDate(), favorite.getCategory()))
//                .toList();
//    }


    public FavoriteItem findOne(long id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pas trouvé"));
        return helper.toFavoriteItem(favorite);
    }

    public List<FavoriteItem> findByCategory(long id) {
        return favoriteRepository.findAll()
                .stream()
                .map(favorite -> helper.toFavoriteItem(favorite))
                .filter(favorite -> favorite.getCategory().getId().equals(id))
                .toList();
    }

    @Override
    public FavoriteItem save(FavoriteDefinition favorite, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Pas trouvé"));

        Favorite entity;

        if (favorite.getId() != null) {
            entity = favoriteRepository.findById(categoryId)
                    .orElseThrow(() -> new NotFoundException("Pas trouvé"));
        } else {
            entity = new Favorite();
        }

        entity = new Favorite(favorite.getId(), favorite.getLabel(), favorite.getLink(),
                new Date(), category);
        entity = favoriteRepository.save(entity);

        return new FavoriteItem(entity.getId(), entity.getLabel(), entity.getLink(),
                new Date(), category);
    }

    @Override
    public void delete(long id) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new NotFoundException("Pas trouvé"));
            favoriteRepository.deleteById(favorite.getId());
    }

    @Override
    public void deleteMultiple(List<Long> ids) {

        ids.forEach(this::delete);
        //ids.forEach(id -> delete(id));
    }


}
