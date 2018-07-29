Feature: Everyone Message Feature
  Background:
    Given I am logged in as "AndroidQa2"
    And I have an existing recipient "Everyone"

  Scenario Outline: Verify I am unable to send an invalid message to everyone
    And I do not enter a valid "<message type>" message
    When I click on the send message button
    Then I should not see the latest message and status

    Examples:
      | message type |
      | blank        |

  Scenario: Verify user is not able to recall a message from everyone
    And I send a valid "random" message
    When I open the message options menu
    Then I should not see the "recall" option

  Scenario: Verify I am able to see the details of a message from everyone
    And I send a valid "random" message
    When I select "details" from the message options
    Then I should see the id, date, and sender of the message

  Scenario: Verify I am able to forward a message from everyone
    And I send a valid "random" message
    And I select "forward" from the message options
    When I select "AndroidQa1" to forward the message to
    Then the message should be sent to "AndroidQa1"

  @cleanup @ignore
  Scenario: Clear conversation in everyone broadcast
    When I clear user conversations
    Then I see no user conversations
