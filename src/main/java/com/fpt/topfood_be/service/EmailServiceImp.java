package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Order;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImp implements EmailService{

    @Autowired
    private OrderService orderService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String sendEmailConfirmPayment(Long orderId) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Order order = orderService.findOrderById(orderId);

            Context context = new Context();
            context.setVariable("order", order);

            String htmlContent = templateEngine.process("email-confirm-payment", context);

            helper.setFrom("phunghoangtest@gmail.com");
            helper.setTo(order.getCustomer().getEmail());
            helper.setSubject("Foody - Payment Success");
            helper.setText(htmlContent, true);

            mailSender.send(message);
            return "success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
