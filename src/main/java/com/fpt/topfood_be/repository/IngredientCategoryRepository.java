package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

    List<IngredientCategory> findByRestaurantId(Long id);
}
