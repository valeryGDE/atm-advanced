Feature: as a user I want to get ability to create filters

  Background: Log in with Default account credentials
    Given the user navigates to Report Portal
    When the user enters Default account login and password
    And the user clicks the Login button
    Then login should be successful

  Scenario Outline: Created filter is displayed on Filters page
    Given the user navigates to Filters tab
    When the user creates new filter with "<Condition>" and "<Condition name>" and "<Filter name>"
    And the user navigates to Filters tab
    Then filter with name "<Filter name>" is displayed on Filters page

    Examples:
      | Condition   | Condition name | Filter name |
      | LAUNCH_NAME | first          | first       |
      | LAUNCH_NAME | third          | third       |


  Rule: Only one filter with a specific name can be saved

    Example: Unable to Save Filter with Duplicate Name
      Given the user navigates to Filters tab
      When the user creates new filter
        | LAUNCH_NAME |
        | second      |
        | second      |
      And the user creates new filter with "LAUNCH_NAME" and "second" and "second" from Launches page
      But filter cannot be saved
      And the user navigates to Filters tab
      Then there should be only one filter with the name "second" on the Filters page