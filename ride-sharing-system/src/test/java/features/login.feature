Feature: User Logs In

Scenario: Logging into account
Given I am a registered user
When I try to login
Then My details are populated