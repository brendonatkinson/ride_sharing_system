Feature: User booking a ride

Scenario: Booking from Search diag
Given I have selected an ordering
When I press book button
Then I am booked on the ride, available seats =-1

Scenario: Booking from viewing ride details
Given I am viewing a ride details
When I press book button
Then I am booked on the ride, available seats =-1