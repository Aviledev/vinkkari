Feature: user can search for book recommendations
  Scenario: user can search
    Given user is at the "home" page
    Then user searches for "kirja" and submits the form
    And user is at the "Search results" page