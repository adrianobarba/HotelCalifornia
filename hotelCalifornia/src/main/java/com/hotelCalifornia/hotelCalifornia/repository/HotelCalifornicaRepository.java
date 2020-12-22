package com.hotelCalifornia.hotelCalifornia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hotelCalifornia.hotelCalifornia.model.HotelCalifornia;

@Repository
public interface HotelCalifornicaRepository extends JpaRepository<HotelCalifornia, Long> {}