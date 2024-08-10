package com.flispark.flightscheduleservice.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flispark.flightscheduleservice.model.FlightSchedule;
import com.flispark.flightscheduleservice.service.FlightScheduleService;

@RestController
@RequestMapping("/api/v1/flight-schedules")
public class FlightScheduleRestController {
    @Autowired
    private FlightScheduleService flightScheduleService;

    // get flight schedule based on flight schedule id
    @GetMapping("/{id}/{bookingDate}")
    public ResponseEntity<?> getFlightSchedule(@PathVariable long id, @PathVariable String bookingDate) {
        FlightSchedule flightSchedule = null;
        try {
            flightSchedule = flightScheduleService.getFlightSchedule(id, bookingDate);
            if (flightSchedule == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(flightSchedule);
        } catch (IllegalArgumentException e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
