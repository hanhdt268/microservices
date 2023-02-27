package com.ratings.service;

import com.ratings.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAll();

    List<Rating> getByUserId(String userId);

    List<Rating> getByHotelId(String hotelId);
}
