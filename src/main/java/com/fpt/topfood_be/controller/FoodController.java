package com.fpt.topfood_be.controller;

import com.fpt.topfood_be.model.Food;
import com.fpt.topfood_be.model.Restaurant;
import com.fpt.topfood_be.model.User;
import com.fpt.topfood_be.request.CreateFoodRequest;
import com.fpt.topfood_be.service.FoodService;
import com.fpt.topfood_be.service.RestaurantService;
import com.fpt.topfood_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
        @RequestParam boolean vegetarian,
        @RequestParam boolean seasonal,
        @RequestParam boolean non_vegetarian,
        @RequestParam(required = false) String food_category,
        @RequestParam Long restaurantId,
        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantsFood(restaurantId,vegetarian,non_vegetarian,seasonal,food_category);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }


}

























