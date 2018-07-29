@env1 @env2 @env3 @env4
Feature: Group message Settings feature

  Background:
    Given I am logged in as "AndroidQa2"
    And I have an existing recipient "Auto Test Existing"

  Scenario: Verify I am able to see the details of a message from an existing group
    And I send a valid "random" message
    When I select "details" from the message options
    Then I should see the id, date, and sender of the message

  Scenario: Verify I am able to forward a message from an existing group
    And I send a valid "random" message
    And I select "forward" from the message options
    When I select "Android1" to forward the message to
    Then the message should be sent to "Android1"

  Scenario Outline: Verify I am able to resend my own message from an existing group
    And I send a valid "random" message
    When I select "<option>" from the message options
    Then I should see the latest message and status

    Examples:
      |option|
      |resend|
#      |resend as priority|

  Scenario: Verify I am able to view the message read by page from an existing group
    And I send a valid "random" message
    When I open the message read by page for the latest message
    Then I should see all of the users message status

  Scenario: Verify able to recall a message from an existing group from search
    And I send a valid "random" message
    When I select "recall" from the message options
    Then I should not see the recalled message

  @cleanup @ignore
  Scenario: Clear conversation with an existing Group
    When I clear user conversations
    Then I see no user conversations
