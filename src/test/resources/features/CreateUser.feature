Feature: Create a new user
  I need a new user account

  Scenario: Creates a new user without errors 
    Given I created a new user with:
      | name                | cpf         | email                     | phone_number |
      | Dino da Silva Sauro | 78005770073 | dinoSilvaSauro@disney.com | 11555563332  |
    When I see a user created
    Then I should be user field "name" filled with "Dino da Silva Sauro"

