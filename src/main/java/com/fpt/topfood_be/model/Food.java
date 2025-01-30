package com.fpt.topfood_be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;
    private Long price;

    private Long discountedPrice;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;

    @ManyToMany
    @JoinTable(
            name = "food_ingredient_category",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_category_id")
    )
    private List<IngredientCategory> ingredientCategories = new ArrayList<>();

//    @ManyToMany
//    private List<IngredientsItem> ingredients = new ArrayList<>();

    private Date creationDate;


}
