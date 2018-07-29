@env1 @env2 @env3 @env4
Feature: Login feature

  Background:
    Given I am on the TT get started screen
    And I select the "Not Now" find Org button

  @smoke
  Scenario: Verify user is able to login
    And I enter the valid credentials for "AndroidUser2"
    When I accept the notification alerts
    Then I should see the roster

  Scenario Outline: Verify the user is unable to login with improper credentials
    And I do not enter the valid credentials for "<credential type>"
    When I accept the notification alerts
    Then I should not see the roster
    Examples:
      | credential type |
      | invalid user    |
      | invalid format  |
      | invalid password|

  @ignore
  Scenario Outline: Verify user is able to login with different accounts
    And I enter the valid email address for "<accounts>"
    When I go through the "<accounts>" authentication
    Then I should see the roster
    Examples:
      | accounts      |
      | SAML          |
#      | USA and Singapore |

  @ignore
  Scenario Outline: Verify user is unable to login with different accounts
    And I enter the valid email address for "<accounts>"
    When I do not go through the "<accounts>" authentication
    Then I should not see the roster
    Examples:
      | accounts      |
      | SAML          |
#      | USA and Singapore |

  Scenario: Verify user is able to click the forgot password link
    And I enter the valid email address for "AndroidUser2"
    When I click the forgot password link
    Then I should see the forgot password page

  Scenario: Verify user is able to login with old password after pressing the forgot password link with sim
    And I enter the valid email address for "AndroidUser2"
    And I click the forgot password link
    And I enter the original password for user "AndroidUser2" by closing the forgot password page
    When I accept the notification alerts
    Then I should see the roster

  @ignore
  Scenario: Verify user is unable to login with invalid password after pressing the forgot password link with sim
    And I enter the valid email address for "AndroidUser2"
    And I click the forgot password link
    When I do not enter the original password for user "AndroidUser2" by closing the forgot password page
    Then I should not see the roster

  @smoke
  Scenario: Verify user is able to log out
    And I enter the valid credentials for "AndroidUser2"
    And I accept the notification alerts
    When I go through the settings "log out" process
    Then I should see the get started screen

  @ignore
  Scenario: Verify user is able to create a new freemium account
    And I enter new credentials
    And I accept the notification alerts
    And I set up my profile
    Then I should see the roster