@prod @staging @env1 @env2 @env3 @env4 @uat
Feature: LoginSettings feature

  Background:
    Given I am on the TT get started screen
    And I select the "Let's Check" find Org button

  Scenario: Verify user is able to login with and existing account from the "We found a match!" screen on a device with a SIM
    When I select "different account" link
    And I enter the valid credentials for "AndroidUser2"
    And I accept the notification alerts
    Then I should see the roster