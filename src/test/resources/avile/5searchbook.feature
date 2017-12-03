Feature: user can search for book recommendations
  Scenario: user can search
    Given user is at the "home" page
    Then user searches for "Programming" and submits the search form
    And user is at the Search results page after searching "Programming"
    Then book with title "C Programming Language, 2nd Edition" is found
    And book with title "Java Programming Tutorial - 2 - Running a Java Program" is found