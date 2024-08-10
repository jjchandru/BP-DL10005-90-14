package com.flispark.flightscheduleservice.model;
/*
{
    "flightSchedules": [
        {
           "id": 522,
           "departure": "LAX",
           "arrival": "JFK",
           "flightNumber": "DL 934",
           "aircraft": "Boeing 767",
           "departureDateTime": "2024-09-11T06:00:00Z",
           "arrivalDateTime": "2024-09-11T14:35:00Z",
           "baseFare": 300,
           "totalSeats": 375,
           "seatsBooked": 25
        }
    ]
}

*/
public class FlightSchedule {
    private long id;
    private String departure;
    private String arrival;
    private String flightNumber;
    private String aircraft;
    private String departureDateTime;
    private String arrivalDateTime;
    private int baseFare;
    private int totalSeats;
    private int seatsBooked;
    private int dynamicFare;

    public FlightSchedule() {
    }

    public FlightSchedule(long id, String departure, String arrival, String flightNumber, String aircraft, String departureDateTime, String arrivalDateTime, int baseFare, int totalSeats, int seatsBooked, int dynamicFare) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.baseFare = baseFare;
        this.totalSeats = totalSeats;
        this.seatsBooked = seatsBooked;
        this.dynamicFare = dynamicFare;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(int baseFare) {
        this.baseFare = baseFare;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public int getDynamicFare() {
        return dynamicFare;
    }

    public void setDynamicFare(int dynamicFare) {
        this.dynamicFare = dynamicFare;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", aircraft='" + aircraft + '\'' +
                ", departureDateTime='" + departureDateTime + '\'' +
                ", arrivalDateTime='" + arrivalDateTime + '\'' +
                ", baseFare=" + baseFare +
                ", totalSeats=" + totalSeats +
                ", seatsBooked=" + seatsBooked +
                ", dynamicFare=" + dynamicFare +
                '}';
    }
    
}
