package com.soues.favoritesproject.service.impl;

import com.soues.favoritesproject.dto.FavoriteDefinition;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.exception.NotFoundException;
import com.soues.favoritesproject.persistence.entity.Category;
import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.persistence.repository.ICategoryRepository;
import com.soues.favoritesproject.persistence.repository.IFavoriteRepository;
import com.soues.favoritesproject.service.IFavoriteService;
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

//    public FavoriteService(IFavoriteRepository favoriteRepository, ICategoryRepository categoryRepository) {
//        this.favoriteRepository = favoriteRepository;
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public List<FavoriteItem> findAll() {
        return favoriteRepository.findAll().stream()
                .map(favorite -> new FavoriteItem(favorite.getId(), favorite.getLabel(),favorite.getLink(), favorite.getDate(), favorite.getCategory()))
                .toList();
    }


//    utile si demande de modifier ? autre ?
//    public FavoriteItem findOne(long id) {
//        Favorite favorite = favoriteRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Pas trouvé"));
//        return new FavoriteItem(favorite.getId(), favorite.getCategory(), favorite.getLabel(), favorite.getLink(), favorite.getDate());
//    }

    public List<FavoriteItem> findByCategory(long id) {
        return favoriteRepository.findAll()
                .stream()
                .map(favorite -> new FavoriteItem(favorite.getId(), favorite.getLabel(), favorite.getLink(), favorite.getDate(),favorite.getCategory()))
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
            entity = new Favorite(favorite.getId(), favorite.getLabel(), favorite.getLink(),
                    new Date(), category);
        }

        entity = favoriteRepository.save(entity);

        return new FavoriteItem(entity.getId(), entity.getLabel(), entity.getLink(),
                new Date(), category);
    }

    @Override
    public void delete(List<Long> listId) {
        listId.forEach(id -> favoriteRepository.deleteById(id));

//        for (Long id:
//             listId) {
//            Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new NotFoundException("Pas trouvé"));
//            favoriteRepository.delete(favorite);
//        }
    }


}
