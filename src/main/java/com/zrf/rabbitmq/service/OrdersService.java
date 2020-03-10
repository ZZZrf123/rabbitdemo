package com.zrf.rabbitmq.service;

import com.zrf.rabbitmq.eneity.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll();

    void createOrder(Orders orders)  throws Exception;
}
