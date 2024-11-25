package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Order;
import com.fpt.topfood_be.response.PaymentResponse;
import com.stripe.exception.StripeException;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentService  {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;
}
