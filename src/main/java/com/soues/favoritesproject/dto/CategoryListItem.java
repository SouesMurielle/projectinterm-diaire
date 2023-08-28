package com.soues.favoritesproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Class used to get CategoryIem from database (id can't be null)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListItem {
    private long id;
    private String label;
    private long references;
}
