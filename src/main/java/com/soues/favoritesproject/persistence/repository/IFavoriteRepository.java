package com.soues.favoritesproject.persistence.repository;

import com.soues.favoritesproject.persistence.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {

//    @Query("select u from favorites f where f.category = ?1")
//    List<Favorite> findByCategory(String category);

}
