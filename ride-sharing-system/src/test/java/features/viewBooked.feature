Feature: User views booked rides

Scenario: Viewing all rides booked
Given I am a registered user
When I am booked on a ride
Then All booked rides can be displayed