package com.shahryarkiani.chatbackend.Messaging;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageService {

    private final RabbitTemplate rabbitTemplate;

    private final FanoutExchange fanoutExchange;

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange){
        this.rabbitTemplate = rabbitTemplate;
        this.fanoutExchange = fanoutExchange;
    }

    public void sendMessage(String msg) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", msg);
    }


}
