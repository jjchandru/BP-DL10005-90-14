package com.flispark.flightscheduleservice.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flispark.flightscheduleservice.model.FlightSchedule;
import com.flispark.flightscheduleservice.model.FlightSchedules;

@Service
public class FlightScheduleService {
    // get flight schedule based on flight schedule id
    public FlightSchedule getFlightSchedule(long id, String bookingDate) throws StreamReadException, DatabindException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // read json file and convert to flight schedule object
        FlightSchedules flightSchedules = objectMapper.readValue(getClass()
            .getClassLoader().getResourceAsStream("data.json"), FlightSchedules.class);
        
        for (FlightSchedule flightSchedule : flightSchedules.getFlightSchedules()) {
            if (flightSchedule.getId() == id) {
                flightSchedule.setDynamicFare(calculateDynamicFare(flightSchedule, bookingDate));
                return flightSchedule;
            }
        }
        return null;
    }

    private int calculateDynamicFare(FlightSchedule flightSchedule, String bookingDate) {
        int diffDays = diffDays(flightSchedule.getDepartureDateTime(), bookingDate);
        int tenDayPeriod = 10 - (int) Math.ceil(diffDays / 10.0);
        if (diffDays < 0 || diffDays > 100) {
            throw new IllegalArgumentException("Booking date not within 100 day range");
        }
        int fareForEachPeriod = flightSchedule.getBaseFare() / 10;
        int firstTenDayPeriodFare = flightSchedule.getBaseFare() / 2;
        int dynamicFare = firstTenDayPeriodFare + (fareForEachPeriod * tenDayPeriod);

        if (diffDays <= 50) {
            double seatOccupanyPercentage = ((double) flightSchedule.getSeatsBooked() / (double) flightSchedule.getTotalSeats()) * 100.0;
            if (seatOccupanyPercentage <= 25) {
                dynamicFare -= 30;
            } else if (seatOccupanyPercentage > 50 && seatOccupanyPercentage <= 75) {
                dynamicFare += 10;
            } else if (seatOccupanyPercentage > 75) {
                dynamicFare += 30;
            }
        }
        return dynamicFare;
    }

    private int diffDays(String date1, String date2) {
        // date1 in format yyyy-MM-dd
        // date2 in format yyyy-MM-ddTHH:mm:ssZ
        date1 = date1.substring(0, 10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d1 = LocalDate.parse(date1, formatter);
        LocalDate d2 = LocalDate.parse(date2, formatter);

        return (int) (d1.toEpochDay() - d2.toEpochDay());
    }
}
