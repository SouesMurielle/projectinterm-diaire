package com.soues.favoritesproject.persistence.repository;

import com.soues.favoritesproject.persistence.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {
}
