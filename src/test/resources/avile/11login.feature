Feature: user can login to site

  Scenario: user can login with correct username and password
    Given user is at the "login" page
    When the entry "admin@admin" is entered into the field "inputEmail"
    And the entry "123456" is entered into the field "inputPassword"
    And  user clicks Login button
    Then user is at the page which contains buttons all and uncheked

  Scenario: user can't login with correct username and incorrect password
    Given user is at the "login" page
    When the entry "admin@admin" is entered into the field "inputEmail"
    And the entry "12345" is entered into the field "inputPassword"
    And  user clicks Login button
    Then user is at the "login" page

  Scenario: user can't login with incorrect username and correct password
    Given user is at the "login" page
    When the entry "admin@admi" is entered into the field "inputEmail"
    And the entry "123456" is entered into the field "inputPassword"
    And  user clicks Login button
    Then user is at the "login" page