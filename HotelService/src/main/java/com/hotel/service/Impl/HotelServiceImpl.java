package com.hotel.service.Impl;

import com.hotel.entity.Hotel;
import com.hotel.exceptions.ResourceNotFoundException;
import com.hotel.repository.HotelRepository;
import com.hotel.service.HotelService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
     private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setHotelId(randomId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return this.hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel with given not found on server"));
    }
}
