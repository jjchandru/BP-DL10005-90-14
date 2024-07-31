# **FliSpart GitHub Copilot Assessment** #

## **Introduction** ##

Welcome to the FliSpark assessment.

FliSpark is an imaginary airline. Currently it incorporates static fare for each flight. It wants to convert its base static fare into dynamic fare. Find below how the dynamic fare will work.

## **Dynamic Fare Rules** ##

Each flight is scheduled for booking 100 days before the actual flight date. Let us split these hundred dates into 10 equal periods. Half of the base fare will be considered for the first 10 days period. In the next 10 days period, 10% of the base fare must be added to the previous 10 days period fare. Likewise, the price increases 10% in each subsequent 10 days period.

Seat occupancy will also be added as a factor for dynamic fare. Let us see how this will work. The seat occupancy factor kicks in only after 50 days of the start of the booking period. The price will be added or reduced based on the below logic.

- If seat occupancy is less than 25%, then $30 will be reduced from the dynamic fare.
- If seat occupancy is between 26% and 50%, then there is no change in the dynamic fare.
- If seat occupancy is between 51% and 75%, then $10 will be added to the dynamic fare.
- If seat occupancy is above 75% then $30 will be added to the dynamic fare.

## **Example #1** ##

The flight data is available in data.json in the root folder of the project. Below the sample data.

```
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
```


Notice that the above flight schedule data does not contain the dynamic fare.

In the request the flight schedule id and date must be passed as input.

Following is the dynamic fare calculation and the JSON response that needs to be sent back.

Flight is scheduled on Sep 11, 2024.

Booking starts from Jun 3, 2024.

The dynamic fare for the first 10 days from 3rd Jun to 12th Jun is:

        basePrice / 2 => $300 / 2 = $150

Let us assume that the booking is made on Jun 13, 2024.

The first 10 day booking range is from 3rd Jun to 12th Jun, hence Jun 13 falls under the second 10 days period.

So, 10% of base fare needs to be added to the first 10 days dynamic fare, which will be:

        $150 + 10% of $300 = $150 + $30 = $180.

13th Jun is before the 50 days booking period; hence the dynamic fare does not change due to seat occupancy, hence the dynamic fare is $180.

## **REST API for Example #1** ##

You have to write a REST API that returns the flight details with dynamic fare.

Sample request:

[http://localhost:9000/api/v1/flight-schedules?id=522&date=2024-06-13](http://localhost:9000/api/v1/flight-schedules?id=522&date=2024-06-13)

So, the JSON response for the above request must be:

```
{
   "id": 522,
   "departure": "LAX",
   "arrival": "JFK",
   "flightNumber": "DL 934",
   "aircraft": "Boeing 767",
   "departureDateTime": "2024-09-11T06:00:00Z",
   "arrivalDateTime": "2024-09-11T14:35:00Z",
   "dynamicFare": 180
 }
 ```
## REST API for Example #2 ##

Let us consider another example, which will include seat occupancy as well when calculating the dynamic fare. Let us assume that the REST API is called on the 2nd Sep, which is exactly 10 days before the flight schedule. The below table shows the dynamic fare for each 10 days period.


| 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|$150|$180|$210|$240|$270|$300|$330|$360|$390|$420|

2nd Sep 2024 falls under the 10th period. Hence the dynamic fare is $420.

If booking date is obviously above 50% of the duration of booking period.

The total seats available is 375 and the booked seat count is 25.

The occupancy percentage is (25 / 375) * 100 = 6.66%.

The occupancy percentage is less than 25%, so $30 must be reduced from the dynamic fare.

The dynamic fare is $420 - $30 = $390.

So, the dynamic fare is $390 and the request and response will be:

[http://localhost:9000/api/v1/flight-schedules?id=522&date=2024-07-13](http://localhost:9000/api/v1/flight-schedules?id=522&date=2024-07-13)

```
{
    "id": 522,
    "departure": "LAX",
    "arrival": "JFK",
    "flightNumber": "DL 934",
    "aircraft": "Boeing 767",
    "departureDateTime": "2024-09-11T06:00:00Z",
    "arrivalDateTime": "2024-09-11T14:35:00Z",
    "dynamicFare": 390
}
```
## Technology Environment ##

The following languages are installed in the evaluation environment.

- JDK 17
- Python 3
- NodeJS 20.16

Hence you can use Java, Python or JavaScript to complete the implementation of the REST API.

## Completion Guidelines ##

1. The REST API endpoint must match the sample endpoints provided above.

2. The port number must be 9000.

3. Provide the start script to start the service in compile-build-and-start-service.bat file provided in the root folder of this project.

4. Commit and Push the code into GitHub.

5. Validate and Submit the code.
