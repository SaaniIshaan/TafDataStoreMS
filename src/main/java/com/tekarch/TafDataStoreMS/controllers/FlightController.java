package com.tekarch.TafDataStoreMS.controllers;

import com.tekarch.TafDataStoreMS.models.Flights;
import com.tekarch.TafDataStoreMS.services.FlightServiceImpl;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/datastore")
@RestController
public class FlightController {

    public static final Logger logger = LogManager.getLogger(FlightServiceImpl.class);

    @Autowired
    private FlightServiceImpl flightServiceImpl;

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
        logger.info("Flight added successfully: {}", addedFlight);
        return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
    }

    // Get available seats for a specific flight
    @GetMapping("/flights/{flightId}/availableSeats")
    public ResponseEntity<Integer> getAvailableSeats(@PathVariable Long flightId) {
        Flights flight = flightServiceImpl.getFlightById(flightId);
        if (flight != null) {
            return ResponseEntity.ok(flight.getAvailableSeats());  // Return the available seats
        }
        return ResponseEntity.notFound().build();  // Return 404 if the flight is not found
    }

    @PutMapping("/flights")
    public ResponseEntity<Flights> updateAFlight(@RequestBody Flights updatedFlight) {
        Flights updatedResponse = flightServiceImpl.updateFlight(updatedFlight);
        if (updatedResponse != null) {
            return ResponseEntity.ok(updatedResponse);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

        @PutMapping("/flights/{flightId}/reduceSeats")
    public ResponseEntity<Void> reduceAvailableSeats(@PathVariable Long flightId) {
        Flights flight = flightServiceImpl.getFlightById(flightId);
        if (flight != null && flight.getAvailableSeats() > 0) {
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);  // Reduce seats by 1
            flightServiceImpl.updateFlight(flight);  // Save the updated flight record
            return ResponseEntity.noContent().build();  // Return 204 No Content
        } else {
            return ResponseEntity.badRequest().body(null);  // Return 400 if no seats are available
        }
    }

    @DeleteMapping("/flights/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        flightServiceImpl.deleteFlight(flightId);
        String message = "Flight with id " + flightId + " has been deleted.";
        return ResponseEntity.ok(message);

    }

    @ExceptionHandler
    public ResponseEntity<String> respondWithError(Exception e) {
        logger.error("Exception Occurred. Details : {}", e.getMessage());
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
