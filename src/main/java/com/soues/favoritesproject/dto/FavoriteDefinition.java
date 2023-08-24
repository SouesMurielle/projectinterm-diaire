package com.soues.favoritesproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDefinition {
    private Long id;
    private String category;
    private String label;
    private String link;
}
