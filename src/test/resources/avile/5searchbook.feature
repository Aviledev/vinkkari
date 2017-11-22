Feature: user can search for book recommendations
  Scenario: user can search
    Given user is at the "home" page
    Then user searches for "kirja" and submits the search form
    And user is at the Search results page after searching "kirja"
    Then book with title "eka kirja" is found
    And book with title "toka kirja" is found