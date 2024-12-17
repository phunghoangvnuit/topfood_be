package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Coupon;
import com.fpt.topfood_be.repository.CouponRepository;
import com.fpt.topfood_be.request.CreateCouponRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CouponServiceImp implements CouponService{
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(CreateCouponRequest req) {
        Coupon coupon = new Coupon();
        coupon.setName(req.getName());
        coupon.setQuantity(req.getQuantity());
        coupon.setValue(req.getValue());
        coupon.setCreatedAt(new Date());
        coupon.setExpiresAt(req.getExpiresAt());

        Coupon savedCoupon = couponRepository.save(coupon);

        return savedCoupon;
    }

    @Override
    public Coupon findCouponById(Long couponId) throws Exception {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);

        if(optionalCoupon.isEmpty()) {
            throw new Exception("coupon not exist...");
        }
        return optionalCoupon.get();
    }
}
