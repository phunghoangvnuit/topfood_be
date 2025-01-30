package com.fpt.topfood_be.request;

import com.fpt.topfood_be.model.Category;
import com.fpt.topfood_be.model.IngredientCategory;
import com.fpt.topfood_be.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;
    private Long discountedPrice;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientCategory> ingredientCategories;
}
