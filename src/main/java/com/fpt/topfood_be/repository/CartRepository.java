package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository <Cart, Long>{

    public Cart findByCustomerId(Long userId);
}
