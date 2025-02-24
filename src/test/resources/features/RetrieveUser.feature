Feature: Retrieves one or more users
  I need get users accounts

  Scenario: Gets a page of users
    Given I created a list of users with:
      | name             | email                          |
      | W. Axl Rose      | axl_rose@gnr.com               |
      | Billie Eilish    | billie.eilish@spotify.com      |
      | Brian Harold May | brian.may@queen.com            |
      | Ozzy Osborne     | ozzy.osborne@black-sabbath.com |
      | Thom Yorke       | thom-yorke@radiohead.com       |
    When I count total of 5 users saved
    Then I should be user field "email" filled with "billie.eilish@spotify.com"
    And I should be user field "email" filled with "axl_rose@gnr.com"
    And I should be user field "email" filled with "thom-yorke@radiohead.com"
    And I should be user field "email" filled with "brian.may@queen.com"
    And I should be user field "email" filled with "ozzy.osborne@black-sabbath.com"

