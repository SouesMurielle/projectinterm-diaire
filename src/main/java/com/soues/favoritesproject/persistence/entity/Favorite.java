package com.soues.favoritesproject.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "favorite")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "category", unique = true, nullable = false)
    private Long category;

    @Column(name = "label", nullable = false)
    private String label;

    @Lob
    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "Update_at")
    private Date date;

}
