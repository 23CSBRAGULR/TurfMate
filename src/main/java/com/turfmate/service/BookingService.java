package com.turfmate.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turfmate.entity.Booking;
import com.turfmate.entity.Turf;
import com.turfmate.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

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

    // public Booking cancelBooking(Long id) {

    //     Booking booking = bookingRepository.findById(id)
    //         .orElseThrow(() -> new RuntimeException("Booking not found"));

    //     booking.setStatus("CANCELLED");

    //     return bookingRepository.save(booking);
    // }

    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public boolean isSlotAvailable(Long turfId, LocalDate date) {
        List<Booking> bookings =
            bookingRepository.findByTurfIdAndDate(turfId, date);

        return bookings.isEmpty();
    }

    public long getTotalBookings() {
        return bookingRepository.count();
    }
}