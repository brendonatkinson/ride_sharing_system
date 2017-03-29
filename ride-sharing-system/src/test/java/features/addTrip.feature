Feature: User creates a new Trip to be shared later.
	
Scenario: Creating a new Trip
Given I am a driver
When I select a route I can add stop times
And Specify the direction of travel
And Indicate recurrency
Then The trip is created but not shared