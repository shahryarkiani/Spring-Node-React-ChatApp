package com.shahryarkiani.chatbackend.Messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shahryarkiani.chatbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/messages")
public class MessageController {

    private final MessageService messageService;

    private final ObjectMapper objectMapper;

    @Autowired
    public MessageController(MessageService messageService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.objectMapper = objectMapper;
    }


    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody Message msg, @AuthenticationPrincipal User user) throws Exception {
        msg.setFrom(user.getUsername());
        System.out.println(msg.toString());
        //messageService.sendMessage(objectMapper.writeValueAsString(msg));
        return ResponseEntity.ok().build();
    }



}
