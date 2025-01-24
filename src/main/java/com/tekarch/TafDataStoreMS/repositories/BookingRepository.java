package com.tekarch.TafDataStoreMS.repositories;

import com.tekarch.TafDataStoreMS.models.Bookings;
import com.tekarch.TafDataStoreMS.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {
        List<Bookings> findByUserId(Long userId);
}
