package com.turfmate.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turfmate.entity.Booking;
import com.turfmate.entity.Turf;
import com.turfmate.repository.BookingRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByTurfIdAndDate(Long turfId, LocalDate date);    
    List<Booking> findByTurfAndDateAndTimeSlot(
        Turf turf,
        LocalDate date,
        String timeSlot

    );
}