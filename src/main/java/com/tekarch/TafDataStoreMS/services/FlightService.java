package com.tekarch.TafDataStoreMS.services;

import com.tekarch.TafDataStoreMS.models.Flights;

import java.util.List;

public interface FlightService {

    Flights addFlight(Flights flights);
    List<Flights> getAllFlights();
    Flights getFlightById(Long flightId);
    Flights updateFlight(Flights flight);
    void deleteFlight(Long flightId);

}
