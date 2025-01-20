package com.tekarch.TafDataStoreMS.controllers;

import com.tekarch.TafDataStoreMS.models.Flights;
import com.tekarch.TafDataStoreMS.services.FlightServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/datastore")
@RestController
public class FlightController {

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    public FlightController(FlightServiceImpl flightServiceImpl){
        this.flightServiceImpl = flightServiceImpl;
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flights>> getAllFlights() {
        return ResponseEntity.ok(flightServiceImpl.getAllFlights());
    }

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<Flights> getFlightById(@PathVariable Long flightId) {
        Flights flight = flightServiceImpl.getFlightById(flightId);
        return flight != null ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @PostMapping("/flights")
    public ResponseEntity<Flights> addFlight(@RequestBody Flights flight) {
        Flights addedFlight = flightServiceImpl.addFlight(flight);
        return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
    }



}
