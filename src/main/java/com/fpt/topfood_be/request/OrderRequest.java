package com.fpt.topfood_be.request;

import com.fpt.topfood_be.model.Address;
import lombok.Data;
import java.util.Date;

@Data
public class OrderRequest {
    private Long restaurantId;
    private String receiverName;
    private String receiverMobile;
    private Address deliveryAddress;
    private Long deliveryFee;
    private Date deliveryAt;
    private String paymentStatus;
}
