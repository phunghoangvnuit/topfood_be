package com.fpt.topfood_be.controller;

import com.fpt.topfood_be.model.IngredientCategory;
import com.fpt.topfood_be.model.IngredientsItem;
import com.fpt.topfood_be.request.IngredientCategoryRequest;
import com.fpt.topfood_be.request.IngredientRequest;
import com.fpt.topfood_be.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/admin/ingredients/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception {
        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping("/admin/ingredients")
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req
    ) throws Exception {
        IngredientsItem item = ingredientsService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getPrice(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/admin/ingredients/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/admin/ingredients/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/admin/ingredients/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/ingredients/food/{foodId}/categories")
    public ResponseEntity<List<IngredientCategory>> getIngredientCategoriesByFoodId(
            @PathVariable Long foodId
    ) throws Exception {
        List<IngredientCategory> categories = ingredientsService.getIngredientCategoriesByFoodId(foodId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/ingredients/category/{categoryId}")
    public ResponseEntity<List<IngredientsItem>> getIngredientsByCategoryId(
            @PathVariable Long categoryId
    ) throws Exception {
        List<IngredientsItem> items = ingredientsService.getIngredientsByCategoryId(categoryId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
