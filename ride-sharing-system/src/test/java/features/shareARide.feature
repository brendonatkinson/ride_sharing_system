Feature: Driver shares a premade ride

Scenario: Sharing a ride that was created earlier
Given I have a trip
When I press share
Then The ride is displayed in the available rides list