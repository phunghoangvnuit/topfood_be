package com.fpt.topfood_be.repository;

import com.fpt.topfood_be.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
