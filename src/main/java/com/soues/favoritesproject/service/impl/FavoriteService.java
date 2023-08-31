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
//    @Service > déclare cette classe comme Service et est gérée par Spring
//    implements permet de créer un lien direct et en temps réel avec l'interface
@Transactional
public class FavoriteService implements IFavoriteService  {

    @Autowired
    private IFavoriteRepository favoriteRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private  DTOHelper helper;

    @Override
    //    permet de surcharger la méthode qui est présente dans l'interface (dans ce qui est "implémenté")
    public List<FavoriteItem> findAll() {
        return favoriteRepository.findAll().stream()
                .map(helper::toFavoriteItem)
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
                .map(helper::toFavoriteItem)
                .filter(favorite -> favorite.getCategory().getId().equals(id))
                .toList();
    }

    @Override
    public FavoriteItem save(FavoriteDefinition definition, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Pas trouvé"));

        Favorite favorite;

        if (definition.getId() != null) {
            favorite = favoriteRepository.findById(definition.getId())
                    .orElseThrow(() -> new NotFoundException("Pas trouvé"));
        } else {
            favorite = new Favorite();
        }

        favorite.setCategory(category);
        favorite.setLink(definition.getLink());
        favorite.setLabel(definition.getLabel());
        favorite.setDate(new Date());

        favorite = favoriteRepository.save(favorite);

        return helper.toFavoriteItem(favorite);
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
