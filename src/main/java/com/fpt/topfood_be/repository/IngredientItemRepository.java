package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {

    List<IngredientsItem> findByRestaurantId(Long id);

    List<IngredientsItem> findByCategoryId(Long categoryId);
}
