package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService{

    @Autowired
    private OrderService orderService;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String sendEmailConfirmOrder(Long orderId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            Order order = orderService.findOrderById(orderId);

            message.setFrom("phunghoangtest@gmail.com");
            message.setTo(order.getCustomer().getEmail());
            message.setSubject("Foody - New Order");
            message.setText("Hello, world!");

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
