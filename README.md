# FliSpark GitHub Copilot Assessment

## Introduction

Welcome to the FliSpark assessment.

FliSpark is an imaginary airline. Currently it incorporates static fare for each flight. It wants to convert its base static fare into dynamic fare. Details about the dynamic fare calculation is explained below.

A REST API needs to be built, that will return flight schedule details with computed dynamic fare.

The REST API can be developed using any one of below mentioned technologies.

| Language   | Software Installed | Command to test software installation in Terminal |
|------------|--------------------|---------------------------------------------------|
| Java       | JDK 17             | ```javac --version```                             |
| Python     | Python 3           | ```python3 --version```                           |
| JavaScript | Node.js 20         | ```node -v```                                     |

Respective language specific software is already installed. Check software installation by running the command provided above, in the terminal.

The developed REST API service must run on port 60000.

## Dynamic Fare Rules

Each flight is scheduled for booking 100 days before the actual flight date. These 100 days duration does not consider the flight's scheduled departure date. These 100 days can be divided into 10 equal periods. Half of the base fare will be considered for the first 10 days period. In the next 10 days period, 10% of the base fare must be added to the previous 10 days period fare. Likewise, the price increases 10% to each subsequent 10 days period on the dynamic fare calculated on the previous period.

Seat occupancy percentage will also be added as a factor for dynamic fare. Let us see how this will work. The seat occupancy factor kicks in only after 50 days of the start of the booking period. The price will be added or reduced based on the below logic.

- If seat occupancy is less than 25%, then $30 will be reduced from the dynamic fare.
- If seat occupancy is between 26% and 50%, then there is no change in the dynamic fare.
- If seat occupancy is between 51% and 75%, then $10 will be added to the dynamic fare.
- If seat occupancy is above 75% then $30 will be added to the dynamic fare.

To avoid discrepancy in fractional data, the test base fare will always be a multiple of 10, hence the base fare and dynamic fare will always be a whole number.

## Test Data

Test data is provided in `data.json` in the root folder of this repository.

Use the test data available in `data.json` as reference for building the REST API.

Consider `data.json` as the database for flight schedules and the REST API must return the specific flight data based on this file.

***IMPORTANT NOTE:*** Do not modify the test data provided in `data.json`. Feel free to add your own test data entries without disturbing the existing entries.

## Example 1

### Input

```
GET
http://localhost:60000/api/v1/flight-schedules/522/2024-06-01
```
The parameter `522` represents the flight schedule id and `2024-06-01` represents the booking date.

Below is the data for flight schedule with id 522 in `data.json`.

```
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
    "seatsBooked": 25,
    "dynamicFare": 0
}
```
### Output

For the above request, it must return the below status code and JSON response.

```
400
{"message":"Booking date not within 100 day range"}
```

### Explanation

The scheduled `departureDateTime` for flight schedule with `id` 522 is `2024-09-11T06:00:00Z`. Departure time is in local time.

The days from `03 Jun 2024` to `10 Sep 2024` is the booking period duration of 100 days.

`Jun - 28 days; Jul - 31 days; Aug - 31 days; Sep - 10 days; 28 + 31 + 31 + 10 = 100 days.`

The booking date provided in the URL `2024-06-01` is two days before the booking start date of `03 Jun 2024`, hence the booking date is not valid, hence the above request must return response status code as 400 with appropriate error message.

## Example 2

### Input

```
GET
http://localhost:60000/api/v1/flight-schedules/522/2024-06-03
```
### Output

For the above request, it must return the below status code and JSON response.

```
200
{
    "id":522,
    "departure":"LAX",
    "arrival":"JFK",
    "flightNumber":"DL 934",
    "aircraft":"Boeing 767",
    "departureDateTime":"2024-09-11T06:00:00Z",
    "arrivalDateTime":"2024-09-11T14:35:00Z",
    "baseFare":400,
    "totalSeats":375,
    "seatsBooked":25,
    "dynamicFare":200
}
```

### Explanation

In the JSON response data, the properties `id`, `departure`, `arrival`, `flightNumber`, `aircraft`, `departureDateTime`, `arrivalDateTime`, `baseFare`, `totalSeats` and `seatsBooked` must exactly match the values provided in `data.json` for flight schedule id 522.

From the explanation in **Example 1**, we know that `3rd Jun 2024` is the date when booking starts.

If the booking date falls in the first 10 day period, then the dynamic fare must be half of the base fare.

For this flight, the base fare is $400, half of this is $200, so the dynamic fare is $200, so in the response it is expected that the `dynamicFare` value must be `200`.

## Example 3

### Input

```
GET
http://localhost:60000/api/v1/flight-schedules/524/2024-08-16
```

### Output

For the above request, it must return the below status code and JSON response.

```
{
    "id":524,
    "departure":"LAX",
    "arrival":"JFK",
    "flightNumber":"DL 934",
    "aircraft":"Boeing 767",
    "departureDateTime":"2024-09-13T06:00:00Z",
    "arrivalDateTime":"2024-09-13T14:35:00Z",
    "baseFare":400,
    "totalSeats":375,
    "seatsBooked":230,
    "dynamicFare":490
}
```
### Explanation

Refer table below, which shows the dynamic fare calculation for each 10 day period, considering `13th Sep` as the departure date.

| 5 Jun | 15 Jun | 25 Jun | 5 Jul | 15 Jul | 25 Jul | 4 Aug | 14 Aug | 24 Aug | 3 Sep |
|-------|--------|--------|-------|--------|--------|-------|--------|--------|-------|
|200|240|280|320|360|400|440|480|520|560|600|

The date displayed, is the starting date of the respective 10 day period. The first 10 day period starts on 5th Jun and ends on 14th Jun. The second 10 day period starts on 15 Jun and ends on 14th Jun.

The above calculation only considers the 10% addition on each 10 day period and does not consider the seat occupancy logic.

The booking date is `2024-08-16`, which falls under the period starting `14 Aug`, so the dynamic fare without seat occupancy consideration is $480.

It is obvious that the booking date is within 2nd half 50 days period, so seat occupancy as well needs to be considered.

`Seat Occupancy % = 230 / 375 * 100 = 61.333%`

The seat occupancy falls under 50% to 75% range, hence $10 needs to be added to the dynamic fare calculated so far.

`Dynamic Fare = $480 + $10 = $490`


## Validation and Submission Guidelines ##

1. Click on the 'Validate' option available in the status bar to initiate validation, which will display the test results in a new window.

2. When clicking 'Validate' option ensure that your services is running in port 60000 and the endpoints are configured exactly similar to the examples provided above.

3. There will be a notification every 20 minutes to denote the timeleft to complete the assessment.

4. The test results of the last Validation is what will be considered as the final result. So ensure that 'Validation' is done frequently which saves your recent changes.

5. If you have completed the test, use "Finish Assessment" option in BanyanPro window to submit your assessment.

6. Once the time duration ends, your assessment is automatically submitted in the BanyanPro window.

7. A random name is assigned to each codespace whenever launched.

8. Incase of eventuality that the codespace window is closed due to unforeseen circumstances follow the below instructions:

   a. Open the assessment repository window, if that window is also closed use the repository link sent in the mail.

   b. Click the green "Code" button.

   c. In the drop down, ensure that "Codespaces" tab is selected.

   d. The "Codespace" tab must list the previously created codespace.

   e. Right click on the respective codespace and select "Open in new tab", which will open the codespace with all the files available when the tab got closed.

9. You can also consider periodically committing and push the code to the repository.
