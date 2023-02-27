package com.userservice;

import com.userservice.entity.Rating;
import com.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private RatingService ratingService;

//    @Test
//    void createRating(){
//        Rating rating = Rating.builder().rating(10).ratingId("").hotelId("").feedback("this is create using feign client").build();
//        this.ratingService.createRating(rating);
//    }



}
