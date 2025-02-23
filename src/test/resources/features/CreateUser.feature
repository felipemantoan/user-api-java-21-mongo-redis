Feature: Create a new user
  I need a new user account

  Scenario: Creates a new user without errors 
    Given I created a new user with:
      | name                | cpf         | email                     | phone_number |
      | Dino da Silva Sauro | 78005770073 | dinoSilvaSauro@disney.com | 11555563332 |
    When I see a user created
    Then I should be "name" filled with "Dino da Silva Sauro"
    And I should be "cpf" filled with "78005770073"
    And I should be "email" filled with "dinoSilvaSauro@disney.com"
    And I should be "phone_number" filled with "11555563332"
