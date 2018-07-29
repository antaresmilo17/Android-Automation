Feature: ForumSetting Part3 Feature
  Background:
    Given I am logged in as "AndroidQa2"

  Scenario: Verify joined forums can be unmuted
    And I have an existing forum "Auto QA Forum"
    And I navigate back to the Inbox Screen
    And I navigate to the "my forums" section
    And I search for the forum
    When I select the "Unmute" option from the Select Action menu
    Then I should see the conversation is "unmuted"

  Scenario: Verify I am able to add member to a joined forum
    And I navigate to the Forums page
    And I join a forum
    And I navigate to the "my forums" section
    And I select the "Add Members" option from the Select Action menu
    Then Selected user "AndroidQa3" was "added" to the forum by the user

