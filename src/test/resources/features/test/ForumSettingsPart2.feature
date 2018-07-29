Feature: ForumSetting Part2 Feature
  Background:
    Given I am logged in as "AndroidQa2"

  Scenario Outline: Verify joined forums can be muted for a given amount of time
    And I have an existing forum "Auto QA Forum"
    And I navigate back to the Inbox Screen
    And I navigate to the "my forums" section
    And I search for the forum
    And I select the "Mute" option from the Select Action menu
    When I mute the conversation for "<duration>"
    Then I should see the conversation is "muted"

    Examples:
    |duration  |
    |15 Minutes|
#    |1 Hour    |
    |8 Hours   |
#    |24 Hours  |
#    |1 Week    |
#    |1 Year    |



