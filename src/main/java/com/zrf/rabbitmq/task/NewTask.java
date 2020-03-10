package com.zrf.rabbitmq.task;


import com.zrf.rabbitmq.contant.Constants;
import com.zrf.rabbitmq.eneity.BrokerMessageLog;
import com.zrf.rabbitmq.eneity.Orders;
import com.zrf.rabbitmq.mapper.BrokerMessageLogMapper;
import com.zrf.rabbitmq.producer.RibbitSender;
import com.zrf.rabbitmq.utils.FastJsonConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class NewTask {

    @Autowired
    private BrokerMessageLogMapper brokerMessageLogMapper;

    @Autowired
    private RibbitSender ribbitSender;

    public void closeOrder(){
        System.out.println("-----------定时任务开始-----------");
        //pull status = 0 and timeout message
        List<BrokerMessageLog> list = brokerMessageLogMapper.query4StatusAndTimeoutMessage();
        list.forEach(messageLog -> {
            if(messageLog.getTryCount() >= 3){
                //update fail message
                brokerMessageLogMapper.changeBrokerMessageLogStatus(messageLog.getMessageId(), Constants.ORDER_SEND_FAILURE, new Date());
            } else {
                // resend
                brokerMessageLogMapper.update4ReSend(messageLog.getMessageId(),  new Date());
                Orders reSendOrder = FastJsonConvertUtil.convertJSONToObject(messageLog.getMessage(), Orders.class);
                try {
                    ribbitSender.sendOrder(reSendOrder);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("-----------异常处理-----------");
                }
            }
        });
    }
}
