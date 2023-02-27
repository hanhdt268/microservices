package com.userservice.service.Impl;

import com.userservice.Repository.UserRepository;
import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.external.services.HotelService;
import com.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;


    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User addUser(User user) {
        String randomId =  UUID.randomUUID().toString();
        user.setUserId(randomId);
        return this.userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        //get user form database with the help of repository
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with given id not found on server" + userId));

        //fetch rating of the above user form rating service
        //http://localhost:8083/ratings/users/af98efad-8491-4712-8399-c6ca612a290f
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());

        List<Rating> ratingsList = ratings.stream().map(rating->{
          //api call to hotel service to get the hotel
            Hotel hotel = this.hotelService.getHotel(rating.getHotelId());
//            logger.info("response status code: {}",);
          //
            rating.setHotel(hotel);
          return rating;
        }).collect(Collectors.toList());

        user.setRatingList(ratingsList);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public void deleteById(String userId) {
        this.userRepository.deleteById(userId);
    }
}
