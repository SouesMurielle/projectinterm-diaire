package com.soues.favoritesproject.service;

import com.soues.favoritesproject.persistence.entity.Favorite;

import java.util.List;

public interface IFavoriteService {

    List<Favorite> findAll();
}
