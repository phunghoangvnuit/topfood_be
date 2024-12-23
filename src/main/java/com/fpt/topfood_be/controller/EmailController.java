package com.fpt.topfood_be.controller;

import com.fpt.topfood_be.model.Order;
import com.fpt.topfood_be.model.User;
import com.fpt.topfood_be.service.EmailService;
import com.fpt.topfood_be.service.OrderService;
import com.fpt.topfood_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class EmailController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/api/send-email/{orderId}")
    public void sendEmailConfirmPayment(@PathVariable Long orderId,
                                        @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        // Logic to check user by jwt
        Order order = orderService.findOrderById(orderId);
        if(Objects.equals(user.getId(), order.getCustomer().getId())){
            emailService.sendEmailConfirmPayment(orderId);
        }
    }
}

