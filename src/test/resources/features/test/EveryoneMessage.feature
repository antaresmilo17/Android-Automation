Feature: Everyone Message Feature
  Background:
    Given I am logged in as "AndroidQa2"
    And I have an existing recipient "Everyone"

  Scenario Outline: : Verify I am able to send a valid message to everyone
    And I enter a valid "<message type>" message
    When I click on the send message button
    Then I should see the latest message and status

    Examples:
      | message type     |
      | random           |
      | emoji            |
      | link             |
      | priority message |

  Scenario Outline: Verify I am able to send a quick reply to everyone
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

  #after the message is resend/resend as priority, the elements are not available (webdriver is unable to find the elements as if they do not exist, but message is visible)
  #this test case works on other feature files
  @ignore
  Scenario Outline: Verify I am able to resend my own message from an existing group
    And I send a valid "random" message
    When I select "<option>" from the message options
    Then I should see the latest message and status

    Examples:
      |option|
      |resend|
      |resend as priority|

  @cleanup @ignore
  Scenario: Clear conversation in everyone broadcast
    When I clear user conversations
    Then I see no user conversations
