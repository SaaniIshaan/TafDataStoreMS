package com.tekarch.TafDataStoreMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id", unique = true, nullable = false,length = 20)
    private Long bookingId;
//    @Column(name="user_id",nullable = false)
    private Long userId;
//    @Column(name="flight_id",nullable = false)
    private Long flightId;
    @Column(name="booking_status",nullable = false)
    private String status; // e.g., Booked, Cancelled
    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
