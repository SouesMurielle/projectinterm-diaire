package com.soues.favoritesproject.utils;

import com.soues.favoritesproject.dto.CategoryItem;
import com.soues.favoritesproject.dto.CategoryListItem;
import com.soues.favoritesproject.dto.FavoriteItem;
import com.soues.favoritesproject.persistence.entity.Category;
import com.soues.favoritesproject.persistence.entity.Favorite;
import com.soues.favoritesproject.persistence.repository.ICategoryRepository;
import com.soues.favoritesproject.persistence.repository.IFavoriteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DTOHelper {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private IFavoriteRepository favoriteRepository;

    private ModelMapper mapper = new ModelMapper();

    public CategoryListItem toCategoryToListItem (Category entity) {
        CategoryListItem dto = mapper.map(entity, CategoryListItem.class);
        dto.setReferences(
                favoriteRepository.findAll()
                        .stream()
                        .filter(f -> f.getCategory().getId().equals(entity.getId()))
                        .count()
        );
        return dto;
    }

    public FavoriteItem toFavoriteItem(Favorite entity) {
        return mapper.map(entity, FavoriteItem.class);
    }

    public CategoryItem toCategoryItem(Category entity) {
        return mapper.map(entity, CategoryItem.class);
    }



}
