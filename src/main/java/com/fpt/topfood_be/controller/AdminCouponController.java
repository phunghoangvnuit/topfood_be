package com.fpt.topfood_be.controller;

import com.fpt.topfood_be.model.Coupon;
import com.fpt.topfood_be.model.User;
import com.fpt.topfood_be.request.CreateCouponRequest;
import com.fpt.topfood_be.service.CouponService;
import com.fpt.topfood_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/coupon")
public class AdminCouponController {
    @Autowired
    private CouponService couponService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Coupon> createCoupon(@RequestBody CreateCouponRequest req,
       @RequestHeader("Authorization") String jwt) throws Exception {
       User user = userService.findUserByJwtToken(jwt);
       Coupon coupon = couponService.createCoupon(req);

       return new ResponseEntity<>(coupon, HttpStatus.CREATED);
    }
}
