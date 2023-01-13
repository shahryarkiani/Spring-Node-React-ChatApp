package com.shahryarkiani.chatbackend.Messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shahryarkiani.chatbackend.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody Message msg, @AuthenticationPrincipal User user) throws JsonProcessingException {
        msg.setFrom(user.getUsername());
        messageService.sendMessage(msg);
        return ResponseEntity.ok().build();
    }



}
