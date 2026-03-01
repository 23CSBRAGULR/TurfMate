package com.turfmate.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.turfmate.entity.Booking;
import com.turfmate.service.BookingService;

@RestController
@RequestMapping("/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // @PutMapping("/cancel/{id}")
    // public Booking cancelBooking(@PathVariable Long id) {
    //     return bookingService.cancelBooking(id);
    // }

    @GetMapping("/availability")
    public String checkAvailability(
            @RequestParam Long turfId,
            @RequestParam String date) {

        LocalDate parsedDate = LocalDate.parse(date);

        boolean available =
            bookingService.isSlotAvailable(turfId, parsedDate);

        return available ? "Slot Available" : "Slot Not Available";
    }

    @GetMapping("/count")
    public long getBookingCount() {
        return bookingService.getTotalBookings();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking Cancelled");
    }
}