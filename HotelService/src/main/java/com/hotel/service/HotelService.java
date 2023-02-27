package com.hotel.service;

import com.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotelById(String hotelId);

}
