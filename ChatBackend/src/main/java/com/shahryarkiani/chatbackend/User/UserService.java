package com.shahryarkiani.chatbackend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String secretKey = "SecretKeyThatShouldBeSetInEnvVariable";

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

    public String generateToken(String username) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String toEncode = username + secretKey;
        byte[] hash = digest.digest(toEncode.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(hash);
    }


    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        for(int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
