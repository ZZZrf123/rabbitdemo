package com.zrf.rabbitmq.mapper;

import com.zrf.rabbitmq.eneity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper {

    public List<Orders> findAll();

    public int insert(Orders orders);

}
