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

    @Column(name = "label", length=100)
    private String label;

//    @Lob    // plus lent lors de la navigation sur mysql
    @Column(name = "link")
    private String link;

    @Column(name = "Update_at", columnDefinition = "date")
    private Date date;
//    columnDefinition : donne des indications à la base de données plutot que ce soit fait "automatiquement"

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Category category;
//    optionnal false signifie qu'on a une relation (11)---(0n)

//    (fetch) EAGER > anticipative : dès qu'on récupère un lien, on récupère la catégorie direct // marche tout le temps mais c'est plus gourmand
//    si on utilise EAGER, il faut absolument penser à mettre un favorite.getCategry() dans le controller quand on récupère favorite de
//    la base de données. Car si on ne fait pas ça, alors on va récupérer un proxy, une sorte d'objet qui signifie que l'obtention de
//    l'objet est en attente
//    (fetch) LAZY > on récupère le lien quand on en aura besoin

//    Cascade.NONE (pas une option car c'est le truc par défaut) > meilleure pratique car il vaut mieux définir ce dont
//    on a besoin plutot que tout avoir en cascade
//    Cascade ALL correspond à l'ensemble des autres valeurs
//    Cascade PERSIST on sauvegarde une catégorie quand on sauvegarde un lien
//    Cascade REMOVE si on supprime un lien, on va supprimer la catégorie (en cascade)
//    Cascade MERGE lorsqu'on sauvegarde un lien, on va chercher à lier une category
//    Cascade REFRESH opération de lecture
//    Cascade DETACH/ATACH une entité est attachée à la base de données dans la couche 3 et 2. Dans la couche 1, l'entité est détachée de la
//    base de données car la couche 1 n'a pas de lien avec la couche 3 (base de données/persistence). ça permet de vérifier si
//    l'opération a été modifiée avant de faire un update. C'est souvent ce qui fait planter l'option FetchType.LAZY en pratique
//    DETACH : Si on détache Favorite, on détache Category en cascade

//    targetEntity : indique ce qu'on lie. Mais redondant car on a déjà l'info avec le type de la variable ci-dessous, ici Category

//    @JoinColum : indique le nom de la clé étrangère. Si ce n'est pas précisé, JPA va le nommer nomdelatable_id par défaut
//    Autre façon de faire (par rapport au "nullable = false")
//    Pour la contrainte d'unicité (pas de liens doublons) -> au début du fichier
//    @Table(name = "favorite", uniqueConstraints = { @UniqueConstraint (name = "UniqueFavoriteLink", columnNames = { "link" }) })
//    Pour la contrainte de clé étrangère -> sur la colonne correspondante
//    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_CATEGORY_FAVORITE))
//    @Fetch(FetchMode.JOIN)                -> Au lieu de faire select favorite et select category (si on avait choisi SELECT)
//                                          -> on fait un join et on a qu'une requete avec les 2 tables liées







//    insertable : false > Interdiction de faire un insert lorsqu'on crée un lien si on indique une catégorie inexistante en base de données

}
