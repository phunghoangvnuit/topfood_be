package com.fpt.topfood_be.controller;

import com.fpt.topfood_be.model.CartItem;
import com.fpt.topfood_be.model.Order;
import com.fpt.topfood_be.model.User;
import com.fpt.topfood_be.request.AddCartItemRequest;
import com.fpt.topfood_be.request.CancelOrderRequest;
import com.fpt.topfood_be.request.OrderRequest;
import com.fpt.topfood_be.response.PaymentResponse;
import com.fpt.topfood_be.service.OrderService;
import com.fpt.topfood_be.service.PaymentService;
import com.fpt.topfood_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req,user);
        PaymentResponse res = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    // Ghi chú bởi: Phùng Hoàng
    // Chỗ này để ở đây là để đổi trạng thái khi người dùng thanh toán xong
    // được navigate về trang Payment Success thì gọi tới để đổi trạng thái
    // (mang tính minh họa chứ thực tế không làm thế này)
    @PutMapping("/order/{id}/payment")
    public ResponseEntity<Order> updatePaymentStatus(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.updatePaymentStatus(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/cancel")
    public ResponseEntity<Order> cancelOrderByUser(
            @RequestBody CancelOrderRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.cancelOrderByUser(req);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id,
                                              @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
