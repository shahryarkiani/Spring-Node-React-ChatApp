package com.shahryarkiani.chatbackend.User;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.TreeSet;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {


            BCryptPasswordEncoder b = new BCryptPasswordEncoder(10);

            String pw = b.encode("THEPASS");


            User u = new User(null, "USER1", pw, new TreeSet<>());
            if(!userRepository.existsByUsername("USER1"))
                userRepository.save(u);

        };
    }

}
