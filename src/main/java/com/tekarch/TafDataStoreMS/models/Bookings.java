package com.tekarch.TafDataStoreMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id", unique = true, nullable = false, length = 20)
    private Long bookingId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "flight_id", nullable = false)
    private Long flightId;
    @Column(name = "booking_status", nullable = false)
    private String status; // e.g., Booked, Cancelled

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}