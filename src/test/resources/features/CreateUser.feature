Feature: Create a new user
  I need a new user account

  Scenario: Creates a new user without errors 
    Given I created a new user
    When I see a user created
    Then I should be cpf filled