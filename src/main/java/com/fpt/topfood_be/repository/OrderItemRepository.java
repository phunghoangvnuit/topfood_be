package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
