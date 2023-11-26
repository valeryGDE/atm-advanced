@Smoke
Feature: as a user I want to get ability to delete filters

  Background: Log in with Admin account credentials
    Given the user navigates to Report Portal
    When the user enters Admin account login and password
    And the user clicks the Login button
    Then login should be successful

  Scenario: Removed filter is not shown on Filters page
    Given the user navigates to Filters tab
    When the user creates new filters
      | Condition   | Condition name | Filter name |
      | LAUNCH_NAME | fourth         | fourth      |
      | LAUNCH_NAME | fifth          | fifth       |
    And the user navigates to Filters tab
    When the user deletes the filter with name "fourth"
    Then filter with name "fifth" is displayed on Filters page
    But filter with name "fourth" is not displayed on Filters page