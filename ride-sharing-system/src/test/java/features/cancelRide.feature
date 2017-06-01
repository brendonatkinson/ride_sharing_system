Feature: User cancels a ride

Scenario: Cancelling a ride
Given I have a booked ride
When I cancel the ride
Then The seat is freed up