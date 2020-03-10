package com.zrf.rabbitmq.service.impl;

import com.zrf.rabbitmq.contant.Constants;
import com.zrf.rabbitmq.eneity.BrokerMessageLog;
import com.zrf.rabbitmq.eneity.Orders;
import com.zrf.rabbitmq.mapper.BrokerMessageLogMapper;
import com.zrf.rabbitmq.mapper.OrdersMapper;
import com.zrf.rabbitmq.producer.RibbitSender;
import com.zrf.rabbitmq.service.OrdersService;
import com.zrf.rabbitmq.utils.DateUtils;
import com.zrf.rabbitmq.utils.FastJsonConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImp implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RibbitSender ribbitSender;


    @Override
    public List<Orders> findAll() {
        return ordersMapper.findAll();
    }


    public void createOrder(Orders orders) throws Exception {
        // 使用当前时间当做订单创建时间（为了模拟一下简化）
        Date orderTime = new Date();
        // 插入业务数据
        ordersMapper.insert(orders);
        // 插入消息记录表数据
        BrokerMessageLog brokerMessageLog = new BrokerMessageLog();
        // 消息唯一ID
        brokerMessageLog.setMessageId(orders.getMessageId());
        // 保存消息整体 转为JSON 格式存储入库
        brokerMessageLog.setMessage(FastJsonConvertUtil.convertObjectToJSON(orders));
        // 设置消息状态为0 表示发送中
        brokerMessageLog.setStatus("0");
        // 设置消息未确认超时时间窗口为 一分钟
        brokerMessageLog.setNextRetry(DateUtils.addMinutes(orderTime, Constants.ORDER_TIMEOUT));
        brokerMessageLog.setCreateTime(new Date());
        brokerMessageLog.setTryCount(0);

        brokerMessageLog.setUpdateTime(new Date());
        brokerMessageLogMapper.insertSelective(brokerMessageLog);
        // 发送消息
        ribbitSender.sendOrder(orders);
    }
}
