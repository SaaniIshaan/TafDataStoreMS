package com.tekarch.TafDataStoreMS.repositories;

import com.tekarch.TafDataStoreMS.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {

}
