package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
