package com.fpt.topfood_be.request;

import com.fpt.topfood_be.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
    private Long deliveryFee;
}
