package com.userservice.service;

import com.userservice.entity.User;

import java.util.List;

public interface UserService {

    //create
    User addUser(User user);

    User getUserById(String userId);

    List<User> getAllUser();

    public void deleteById(String userId);
}
