package com.fpt.topfood_be.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateCouponRequest {
    private String name;
    private int quantity;
    // discounted value
    private Long value;
    private Date createdAt;
    private Date expiresAt;
}
