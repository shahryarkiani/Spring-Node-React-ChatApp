package com.shahryarkiani.chatbackend.Messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;

    private final FanoutExchange fanoutExchange;

    private final MessageRepository messageRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange, MessageRepository messageRepository){
        this.rabbitTemplate = rabbitTemplate;
        this.fanoutExchange = fanoutExchange;
        this.messageRepository = messageRepository;
        objectMapper = new ObjectMapper();
    }

    public void sendMessage(Message msg) throws JsonProcessingException {
        messageRepository.save(msg);
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", objectMapper.writeValueAsString(msg));
    }


}
