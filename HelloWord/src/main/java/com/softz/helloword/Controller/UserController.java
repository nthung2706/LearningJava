package com.softz.helloword.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softz.helloword.entity.User;
import com.softz.helloword.service.UserService;

@RestController 
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("users")
    public List<User> getUser(){
            return userService.getUsers();
    }
}
