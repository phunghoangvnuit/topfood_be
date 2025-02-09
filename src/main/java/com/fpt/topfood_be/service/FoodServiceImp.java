package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Category;
import com.fpt.topfood_be.model.Food;
import com.fpt.topfood_be.model.Restaurant;
import com.fpt.topfood_be.repository.FoodRepository;
import com.fpt.topfood_be.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService{

    @Autowired
    private FoodRepository foodRepository;


    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setDiscountedPrice(req.getDiscountedPrice());
        food.setIngredientCategories(req.getIngredientCategories());
        food.setSeasonal(req.isSeasonal());
        food.setCreationDate(new Date());
        food.setVegetarian(req.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

            Food food = findFoodById(foodId);
            food.setRestaurant(null);
            foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId,
                                                                          boolean isVegetarian,
                                                                          boolean isNonVegetarian,
                                                                          boolean isSeasonal, String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegetarian){
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if(isNonVegetarian){
            foods = filterByNonVegetarian(foods, isNonVegetarian);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory!=null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods, boolean isNonVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    private List<Food> filterByAvailable(List<Food> foods, boolean isAvailable) {
        return foods.stream().filter(food -> food.isAvailable() == isAvailable).collect(Collectors.toList());
    }

    private List<Food> filterByNotAvailable(List<Food> foods) {
        return foods.stream().filter(food -> !food.isAvailable()).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword, Boolean isAvailable) {

        List<Food> foods = foodRepository.searchFood(keyword);

        if (isAvailable != null) {
            if (isAvailable) {
                foods = filterByAvailable(foods, isAvailable);
            } else {
                foods = filterByNotAvailable(foods);
            }
        }

        return foods;
    }

    @Override
    public List<Food> searchFoodByRestaurant(Long restaurantId, String keyword, Boolean isAvailable) {

        List<Food> foods = foodRepository.searchFoodByRestaurant(restaurantId, keyword);

        if (isAvailable != null) {
            if (isAvailable) {
                foods = filterByAvailable(foods, isAvailable);
            } else {
                foods = filterByNotAvailable(foods);
            }
        }

        return foods;
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);

        if(optionalFood.isEmpty()) {
            throw new Exception("food not exist...");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);

    }
}











