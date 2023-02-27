package com.ratings.controller;


import com.ratings.entity.Rating;
import com.ratings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.OK).body(this.ratingService.createRating(rating));
    }

    @GetMapping("/")
    public ResponseEntity<List<Rating>> getAll(){
        return ResponseEntity.ok(this.ratingService.getAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.ok(this.ratingService.getByUserId(userId));
    }


    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable("hotelId") String hotelId){
        return ResponseEntity.ok(this.ratingService.getByHotelId(hotelId));
    }
}
