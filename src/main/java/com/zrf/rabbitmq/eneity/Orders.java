package com.zrf.rabbitmq.eneity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Orders  implements Serializable {


    private static final long serialVersionUID = 4063979118454961670L;

    private String id;
    private String name;

    private String messageId;  //存储消息发送的唯一标识


}
