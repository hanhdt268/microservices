package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.service.Impl.UserServiceImpl;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable("userId")String userId){
        this.userService.deleteById(userId);
    }
}
