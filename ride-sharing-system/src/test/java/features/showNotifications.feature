Feature: Notification System

Scenario: Logging in, new notifications displayed
Given I am a registered user
When I log in
Then Relevant notifications are displayed