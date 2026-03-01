package com.turfmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turfmate.entity.Payment;

import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT SUM(p.totalAmount) FROM Payment p WHERE p.status='PAID'")
    Double calculateTotalRevenue();

}