package com.turfmate.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "turf_id")
    private Turf turf;

    private LocalDate date;

    private String timeSlot; // Example: "6AM-7AM"

    private String status; // BOOKED / CANCELLED
}