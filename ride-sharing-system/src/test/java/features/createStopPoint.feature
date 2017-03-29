Feature: User creates a new Stop Point

Scenario: Creating a new stop point
Given A street address
When A stop point is created
And The address is unique
Then The route is added to the data source