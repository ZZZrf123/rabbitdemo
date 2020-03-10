package com.zrf.rabbitmq.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zrf.rabbitmq.eneity.Orders;

public class FastJsonConvertUtil {

    public static Orders convertJSONToObject(String message, Object obj){
        Orders order = JSON.parseObject(message, new TypeReference<Orders>() {});
        return order;
    }

    public static String convertObjectToJSON(Object obj){
        String text = JSON.toJSONString(obj);
        return text;
    }
}
