package com.tekarch.TafDataStoreMS.repositories;

import com.tekarch.TafDataStoreMS.models.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flights, Long> {

}
