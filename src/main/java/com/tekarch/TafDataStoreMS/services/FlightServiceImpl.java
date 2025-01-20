package com.tekarch.TafDataStoreMS.services;

import com.tekarch.TafDataStoreMS.models.Flights;
import com.tekarch.TafDataStoreMS.repositories.FlightRepository;
import com.tekarch.TafDataStoreMS.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository =flightRepository;
    }

    @Override
    public Flights addFlight(Flights flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flights> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flights getFlightById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public Flights updateFlight(Flights flight) {
        return flightRepository.save(flight);   //save the updated flight
    }

    @Override
    public void deleteFlight(Long flightId) {
        flightRepository.deleteById(flightId);
    }
}
