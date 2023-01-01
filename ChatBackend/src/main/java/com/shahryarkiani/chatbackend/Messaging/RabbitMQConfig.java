package com.shahryarkiani.chatbackend.Messaging;


import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("chat.fanout", false, false);
    }

    @Bean
    public MessageService messageSender(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange){
        return new MessageService(rabbitTemplate, fanoutExchange);
    }

}
