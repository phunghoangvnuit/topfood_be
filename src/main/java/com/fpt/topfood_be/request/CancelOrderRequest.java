package com.fpt.topfood_be.request;

import lombok.Data;

@Data
public class CancelOrderRequest {
    private Long id;
    private String reason;
}
