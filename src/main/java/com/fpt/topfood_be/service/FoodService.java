package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Category;
import com.fpt.topfood_be.model.Food;
import com.fpt.topfood_be.model.Restaurant;
import com.fpt.topfood_be.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                                                     boolean isVegetarian,
                                                                     boolean isNonVegetarian,
                                                                     boolean isSeasonal,
                                                                     String foodCategory
    );

    public List<Food> searchFood(String keyword, Boolean isAvailable);
    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
