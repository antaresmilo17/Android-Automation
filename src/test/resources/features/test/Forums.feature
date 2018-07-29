@env1 @env2 @env3 @env4
Feature: Forums Feature
  Background:
    Given I am logged in as "AndroidQa2"
    And I have an existing forum "Auto QA Forum"

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
