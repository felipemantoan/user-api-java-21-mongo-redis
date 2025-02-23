Feature: Create a new user
  I need a update user account

  Background: 
    Given I created a new user with:
      | name       |
      | Doug Funny |
    When I see a user created
    Then I should be user field "name" filled with "Doug Funny"

  Scenario: Updates an user without errors 
    Given I update latest created user with:
      | name              | email                        | phone_number |
      | Skeeter Valentine | skeeter.valentine@disney.com | 90123450987  |
    When I see a user updated
    Then I should be user field "name" filled with "Skeeter Valentine"
    Then I should be user field "email" filled with "skeeter.valentine@disney.com"
    Then I should be user field "phone_number" filled with "90123450987"
