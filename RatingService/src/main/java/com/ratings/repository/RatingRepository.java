package com.ratings.repository;

import com.ratings.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {

    List<Rating> findByHotelId(String hotelId);

    List<Rating> findByUserId(String userId);
}
