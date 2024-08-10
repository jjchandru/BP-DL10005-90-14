package com.flispark.flightscheduleservice.model;

public class FlightSchedules {
    private FlightSchedule[] flightSchedules;

    public FlightSchedules() {
    }

    public FlightSchedules(FlightSchedule[] flightSchedules) {
        this.flightSchedules = flightSchedules;
    }

    public FlightSchedule[] getFlightSchedules() {
        return flightSchedules;
    }

    public void setFlightSchedules(FlightSchedule[] flightSchedules) {
        this.flightSchedules = flightSchedules;
    }    
}
