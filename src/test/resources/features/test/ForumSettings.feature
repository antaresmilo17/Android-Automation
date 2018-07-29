Feature: ForumSetting Feature
  Background:
    Given I am logged in as "AndroidQa2"

  Scenario: Verify forum sections are displayed
    When I navigate to the Forums page
    Then I should see all forum sections

  Scenario: Verify forums are displayed in the explore section
    And I have an existing forum "Auto QA Forum"
    And I navigate back to the Inbox Screen
    And I navigate to the "explore" section
    When I search for the forum
    Then The forum is displayed

  Scenario: Verify forums are displayed in the forums section
    And I have an existing forum "Auto QA Forum"
    And I navigate back to the Inbox Screen
    And I navigate to the "my forums" section
    When I search for the forum
    Then The forum is displayed

  Scenario: Verify I am able to join a forum from the forums page
    And I navigate to the Forums page
    And I join a forum
    And I navigate to the "explore" section
    When I search for the forum
    Then I am able to "join" a forum

  Scenario: Verify leaving forum changes the button from "Joined" to "Join" in the explore section
    And I navigate to the Forums page
    And I join a forum
    And I navigate to the "my forums" section
    And I select the "Leave" option from the Select Action menu
    And I navigate to the "explore" section
    When I search for the forum
    Then I am able to "leave" a forum

  Scenario: Verify leaving forum gets removed from the my forums section
    And I navigate to the Forums page
    And I join a forum
    And I navigate to the "my forums" section
    And I select the "Leave" option from the Select Action menu
    And I navigate to the "my forums" section
    When I search for the forum
    Then The forum is not displayed