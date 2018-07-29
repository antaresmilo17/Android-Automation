@prod @staging @env1 @env2 @env3 @env4 @uat
Feature: P2P feature

  Background:
    Given I am logged in as "AndroidQa2"
    And I have an existing recipient "Android1"

  @smoke
  Scenario Outline: : Verify I am able to send a valid message to an existing user
    And I enter a valid "<message type>" message
    When I click on the send message button
    Then I should see the latest message and status

    Examples:
    | message type     |
    | random           |
    | link             |
    | priority message |
    | emoji            |

  Scenario Outline: Verify I am unable to send an invalid message to an existing user
    And I enter a valid "<message type>" message
    When I click on the send message button
    Then I should not see the latest message and status

    Examples:
      | message type |
      | blank        |

  @ignore
  Scenario: Verify invalid messages sent are not sent and rejected
    And I enter a valid "invalid" message
    When I click on the send message button
    Then Invalid message is not sent and rejected

  Scenario: Verify I am able to see a message's message details
    And I send a valid "random" message
    When I select "details" from the message options
    Then I should see the id, date, and sender of the message

  Scenario: Verify I am able to forward a message to an existing user
    And I send a valid "random" message
    When I forward the message to "Android1"
    Then the message should be sent to "Android1"

  Scenario Outline: Verify I am able to resend my own message to an existing conversation
    And I send a valid "random" message
    When I select "<option>" from the message options
    Then I should see the latest message and status

    Examples:
      |option|
      |resend|
      |resend as priority|

  Scenario Outline: Verify I am able to send a quick reply to an existing conversation
    And I select the "<quick reply>" option
    When I send the "<quick reply>" option
    Then I should see the latest message and status

    Examples:
      |quick reply|
      |first|
      |second|
      |third|

  Scenario: Verify I am able to recall a message
    And I send a valid "random" message
    When I select "recall" from the message options
    Then I should not see the recalled message