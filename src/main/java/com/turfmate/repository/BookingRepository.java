package com.turfmate.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turfmate.entity.Booking;
import com.turfmate.entity.Turf;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByTurfIdAndBookingTime(Long turfId, LocalDateTime bookingTime);
    
    List<Booking> findByTurfAndDateAndTimeSlot(
        Turf turf,
        LocalDate date,
        String timeSlot
    );
}