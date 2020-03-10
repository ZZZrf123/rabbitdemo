package com.zrf.rabbitmq.producer;

import com.zrf.rabbitmq.contant.Constants;
import com.zrf.rabbitmq.eneity.Orders;
import com.zrf.rabbitmq.mapper.BrokerMessageLogMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RibbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    public void order(Orders orders) {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(orders.getId());
        rabbitTemplate.convertAndSend("order-exchange",
                "order.abcd", // key
                orders,
                correlationData
                ); // 消息唯一id

    }


    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            String messageId = correlationData.getId();
            if(ack){
                //如果confirm返回成功 则进行更新
                System.err.println("回调成功："+ correlationData.getId());
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageId, Constants.ORDER_SEND_SUCCESS, new Date());
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                System.err.println("异常处理...");
            }
        }
    };

    public void sendOrder(Orders orders) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(orders.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange",
                "order.abcd", // key
                orders,
                correlationData
        ); // 消息唯一id

    }

}
