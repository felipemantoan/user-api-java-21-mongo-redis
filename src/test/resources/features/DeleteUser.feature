Feature: Deletes existing user
  I need a delete user account

  Background: 
    Given I created a new user with:
      | name             | email                       |
      | Patti Mayonnaise | patti.mayonnaise@disney.com |
    When I see a user created
    And I should be user field "email" filled with "patti.mayonnaise@disney.com"
  Scenario: Deletes an user without errors
    Given I delete latest created user
    When I can't see a user deleted
    Then I can't get a user
