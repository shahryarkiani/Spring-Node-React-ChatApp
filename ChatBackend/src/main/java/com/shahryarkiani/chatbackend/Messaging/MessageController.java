package com.shahryarkiani.chatbackend.Messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/message")
public class MessageController {

    private final MessageService messageService;

    private final ObjectMapper objectMapper;

    @Autowired
    public MessageController(MessageService messageService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.objectMapper = objectMapper;
    }


    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody Message msg) throws Exception {
        messageService.sendMessage(objectMapper.writeValueAsString(msg));
        return ResponseEntity.ok().build();
    }

}
