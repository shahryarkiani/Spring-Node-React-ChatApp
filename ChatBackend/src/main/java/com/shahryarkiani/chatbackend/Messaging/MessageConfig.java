package com.shahryarkiani.chatbackend.Messaging;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    CommandLineRunner messageCommandLineRunner(MessageRepository messageRepository) {
        return args -> {

            messageRepository.deleteAll();

            Message msg1 = new Message("joe", "TEXT_BODY");
            msg1.setFrom("bob");
            Message msg2 = new Message("bob", "TEXT_BODY");
            msg2.setFrom("joe");
            Message msg3 = new Message("joe", "TEXT_BODY");
            msg3.setFrom("notbob");
            Message msg4 = new Message("joe", "TEXT_BODY");
            msg4.setFrom("notjoe");

            messageRepository.save(msg1);
            messageRepository.save(msg2);
            messageRepository.save(msg3);
            messageRepository.save(msg4);

            System.out.println(messageRepository.getMessagesBetweenUseres("joe", "bob"));

        };
    }

}