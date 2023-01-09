package com.shahryarkiani.chatbackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean registerUser(User user) {
        if(user == null || user.getUsername() == null || user.getPassword() == null || userRepository.existsByUsername(user.getUsername()))
            return false;

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Authenticating for: " + username);
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new UsernameNotFoundException(username);
        }


    }
}
