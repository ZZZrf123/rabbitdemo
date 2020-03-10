package com.zrf.rabbitmq.controller;


import com.zrf.rabbitmq.eneity.Orders;
import com.zrf.rabbitmq.mapper.OrdersMapper;
import com.zrf.rabbitmq.producer.RibbitSender;
import com.zrf.rabbitmq.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class TestController {



    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/test")
    public List<Orders> test(){
        return ordersService.findAll();
    }


    @Autowired
    private RibbitSender ribbitSender;
    @RequestMapping("/go")
    public void go() {
        Orders orders = new Orders();

        orders.setId("20201");
        orders.setName("我是一个订单");
        orders.setMessageId(System.currentTimeMillis() +"$" + UUID.randomUUID().toString());
        ribbitSender.order(orders);

    }

    @RequestMapping("/add")
    public void add(Orders orders)throws Exception{
        ordersService.createOrder(orders);
    }
}
