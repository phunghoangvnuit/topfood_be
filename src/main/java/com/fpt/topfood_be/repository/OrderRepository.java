package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public List<Order> findByCustomerId(Long userId);

    public List<Order> findByRestaurantId(Long restaurantId);

    @Query("SELECT o FROM Order o WHERE o.restaurant.id = :restaurantId AND (o.customer.fullName LIKE %:keyword% OR o.customer.mobile LIKE %:keyword%) AND (:status IS NULL OR :status = '' OR o.orderStatus = :status)")
    List<Order> searchOrdersByRestaurantIdAndKeywordAndStatus(@Param("restaurantId") Long restaurantId, @Param("keyword") String keyword, @Param("status") String status);
}
