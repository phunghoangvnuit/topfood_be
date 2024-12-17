package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Coupon;
import com.fpt.topfood_be.request.CreateCouponRequest;

public interface CouponService {
    public Coupon createCoupon(CreateCouponRequest req);

    public Coupon findCouponById(Long couponId) throws Exception;
}
