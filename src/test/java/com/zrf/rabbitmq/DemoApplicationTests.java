package com.zrf.rabbitmq;

import com.zrf.rabbitmq.eneity.Orders;
import com.zrf.rabbitmq.producer.RibbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private RibbitSender ribbitSender;

    @Test
    public void contextLoads() throws Exception {
        Orders orders = new Orders();

        orders.setId("20201");
        orders.setName("我是一个订单");
        orders.setMessageId(System.currentTimeMillis() +"$" + UUID.randomUUID().toString());
        ribbitSender.sendOrder(orders);
    }

}
