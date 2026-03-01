package com.turfmate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.turfmate.entity.Booking;
import com.turfmate.entity.Payment;
import com.turfmate.repository.BookingRepository;
import com.turfmate.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Payment processPayment(Payment payment) {

        // Fetch full booking from DB
        Booking booking = bookingRepository
                .findById(payment.getBooking().getId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Double pricePerHour = booking.getTurf().getPricePerHour();

        Double totalAmount = pricePerHour;

        Integer players = payment.getNumberOfPlayers();

        Double split = totalAmount / players;

        payment.setBooking(booking);
        payment.setTotalAmount(totalAmount);
        payment.setSplitAmount(split);
        payment.setStatus("PAID");

        return paymentRepository.save(payment);
    }

    public Double getTotalRevenue() {
        return paymentRepository.calculateTotalRevenue();
    }
}