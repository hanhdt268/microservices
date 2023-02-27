package com.userservice.controller;

import com.userservice.entity.User;
import com.userservice.service.Impl.UserServiceImpl;
import com.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


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
    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<?> getUserById(@PathVariable("userId") String userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    //creating fallback method for circuitBreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("fallback is executed because service down: ", ex.getMessage());
        User user = User.builder().userId("123123").name("Chery").email("chery@gmail.com").about("This is create user " +
                "chery because some service is down").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable("userId")String userId){
        this.userService.deleteById(userId);
    }
}
