Feature: user can search for book recommendations
  Scenario: user can search
    Given user is at the "home" page
    Then user searches for "kirja" and submits the search form
    And user is at the "Search results" page
    Then book with title "TestiKirjaM" is found