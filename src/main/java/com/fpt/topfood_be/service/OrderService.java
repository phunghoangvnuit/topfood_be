package com.fpt.topfood_be.service;

import com.fpt.topfood_be.model.Order;
import com.fpt.topfood_be.model.User;
import com.fpt.topfood_be.request.OrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long userId) throws Exception;

    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;

    public Order findOrderById (Long orderId) throws Exception;
}
