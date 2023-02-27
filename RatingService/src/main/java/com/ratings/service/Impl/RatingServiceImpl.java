package com.ratings.service.Impl;

import com.ratings.entity.Rating;
import com.ratings.repository.RatingRepository;
import com.ratings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(Rating rating) {
         String randomId = UUID.randomUUID().toString();
         rating.setRatingId(randomId);
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAll() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getByUserId(String userId) {
        return this.ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getByHotelId(String hotelId) {
        return this.ratingRepository.findByHotelId(hotelId);
    }
}
