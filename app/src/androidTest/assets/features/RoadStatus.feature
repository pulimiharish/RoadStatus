Feature: Road Status
  As a user, I want to be able to enter any road in the required field
  So that the app can get the current status and display the current status

  @smoke
  Scenario: Submitting a valid road id and checking for the name
    Given I am on the Home Screen
    When I enter a valid road ID
    Then I see displayName is displayed

  @acceptance
  Scenario: Submitting a valid road id and checking for the status
    Given I am on the Home Screen
    When I enter a valid road ID
    Then I see statusSeverity is displayed

  @acceptance
  Scenario: Submitting a valid road id and checking for the description
    Given I am on the Home Screen
    When I enter a valid road ID
    Then I see statusSeverityDescription is displayed

  @acceptance
  Scenario: Submitting an invalid road id and checking for the error
    Given I am on the Home Screen
    When I enter an invalid road ID
    Then I see an informativeError is displayed
