package com.userservice.external.services;


import com.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    //create
    @PostMapping("/ratings/")
    Rating createRating(Rating values);

    //put

    //delete
    @DeleteMapping("/rating/{ratingId}")
    public void deleteRating(@PathVariable("ratingId") String ratingId);

}
