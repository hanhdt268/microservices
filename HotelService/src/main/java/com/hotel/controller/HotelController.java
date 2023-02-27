package com.hotel.controller;

import com.hotel.entity.Hotel;
import com.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.createHotel(hotel));
    }

    @GetMapping("/")
    public ResponseEntity<List<Hotel>> getHotel(){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getAllHotel());
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotelById(@PathVariable("hotelId") String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(this.hotelService.getHotelById(hotelId));
    }
}
