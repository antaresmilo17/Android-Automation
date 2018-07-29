@env1 @env2 @env3 @env4
Feature: Group message feature

  Background:
    Given I am logged in as "AndroidQa2"
    And I have an existing recipient "Auto Test Existing"

  @smoke
  Scenario Outline: : Verify I am able to send a valid message to an existing user
    And I enter a valid "<message type>" message
    When I click on the send message button
    Then I should see the latest message and status

    Examples:
      | message type     |
      | random           |
      | emoji            |
      | link             |
      | priority message |

  Scenario Outline: Verify I am unable to send an invalid message to an existing group
    And I do not enter a valid "<message type>" message
    When I click on the send message button
    Then I should not see the latest message and status

    Examples:
      | message type |
      | blank        |

  Scenario Outline: Verify I am able to send a quick reply to an existing group
    And I select the "<quick reply>" option
    When I send the "<quick reply>" option
    Then I should see the latest message and status

    Examples:
      |quick reply|
      |first|
      |second|
      |third|

  Scenario Outline: Verify I am able to send different types of messages to an existing conversation
    And I select a "<attachment type>" message
    When I send a "<attachment type>" message
    Then I should see the latest attachment message and status
    Examples:
      |attachment type          |
      |saved picture            |
      |recorded audio           |
      |location                 |
#      |priority picture         |
#      |priority saved picture   |
#      |priority recorded audio  |
#      |priority location        |
#      |document word|
#      |document pdf|
#      |document excel|
#      |document powerpoint|
#      |priority document word|
#      |priority document pdf|
#      |priority document excel|
#      |priority document powerpoint|

  Scenario: Verify I am able to delete a message from another user from an existing group
    And I send a "random" message to "Auto Test Existing" through the API
    When I select "recall" from the message options
    Then I should not see the latest message and status

  @ignore
  Scenario: Verify a push notification appears on the device
    And I navigate back to the Inbox Screen
    When I send a "random" message to "Auto Test Existing" through the API
    Then I should see the push notification on the device

  @cleanup @ignore
  Scenario: Clear conversation with an existing Group
    When I clear user conversations
    Then I see no user conversations
