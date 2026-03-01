package com.turfmate.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turfmate.entity.Booking;
import com.turfmate.entity.Turf;
import com.turfmate.repository.BookingRepository;
import com.turfmate.repository.TurfRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TurfRepository turfRepository;

    public Booking createBooking(Booking booking) {

        Turf turf = booking.getTurf();
        LocalDate date = booking.getDate();
        String timeSlot = booking.getTimeSlot();

        List<Booking> existing = bookingRepository
                .findByTurfAndDateAndTimeSlot(turf, date, timeSlot);

        if (!existing.isEmpty()) {
            throw new RuntimeException("Slot already booked!");
        }

        booking.setStatus("BOOKED");

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("CANCELLED");

        return bookingRepository.save(booking);
    }

    public boolean isSlotAvailable(Long turfId, LocalDateTime bookingTime) {
        List<Booking> bookings =
            bookingRepository.findByTurfIdAndBookingTime(turfId, bookingTime);

        return bookings.isEmpty();
    }

    public long getTotalBookings() {
        return bookingRepository.count();
    }
}