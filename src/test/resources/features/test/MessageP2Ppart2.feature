@env1 @env2 @env3 @env4
Feature: P2P feature part 2

  Background:
    Given I am logged in as "AndroidQa2"
    And I have an existing recipient "Android1"

  Scenario Outline: Verify I am able to send different types of messages to an existing conversation
    And I select a "<attachment type>" message
    When I send a "<attachment type>" message
    Then I should see the latest attachment message and status
    Examples:
      |attachment type          |
      |saved picture            |
      |recorded audio           |
      |location                 |

  @ignore
  Scenario Outline: Verify I am able to send different types of priority messages to an existing conversation
    And I select a "<attachment type>" message
    When I send a "<attachment type>" message
    Then I should see the latest attachment message and status
    Examples:
      |attachment type          |
      |priority picture         |
      |priority saved picture   |
      |priority recorded audio  |
      |priority location        |


#      |document word|
#      |document pdf|
#      |document excel|
#      |document powerpoint|
#      |priority document word|
#      |priority document pdf|
#      |priority document excel|
#      |priority document powerpoint|

  Scenario: Verify I am able to delete a message from another user
    And I send a "random" message to "AndroidQa2" through the API
    When I select "recall" from the message options
    Then I should not see the latest message and status

  @ignore
  Scenario: Verify a push notification appears on the device
    And I navigate back to the Inbox Screen
    When I send a "random" message to "AndroidQa2" through the API
    Then I should see the push notification on the device

  @ignore
  Scenario: Verify I am able to receive a message from another user
    And I navigate back to the Inbox Screen
    When I send a "random" message to "AndroidQa2" through the API
    Then I should see the latest message and status

  @cleanup @ignore
  Scenario: Clear conversation with an existing user
    When I clear user conversations
    Then I see no user conversations