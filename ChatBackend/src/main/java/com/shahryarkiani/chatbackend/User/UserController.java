package com.shahryarkiani.chatbackend.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAuthenticationToken(@AuthenticationPrincipal User user) throws NoSuchAlgorithmException {
        ObjectNode response = objectMapper.createObjectNode();
        response.put("username", user.getUsername());
        response.put("token", userService.generateToken(user.getUsername()));
        return response.toPrettyString();
    }

    @GetMapping(value = "/friends")
    public UserRepository.friendData getFriends(@AuthenticationPrincipal User user) {
        return userService.getFriends(user);
    }

    @PostMapping(value = "/friends")
    public void addFriend(@AuthenticationPrincipal User user, @RequestBody friendRequest req) {
        userService.addFriend(user, req.newFriendName());
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody User user) {
        if(!userService.registerUser(user))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else
            return ResponseEntity.ok(null);
    }

    private record friendRequest(String newFriendName) {}

}
