Feature: user can remove a course

  Scenario: user can find a page where user can remove a selected recommendation
    Given user is at the "home" page
    When user navigates to "Courses"
    And user clicks "Kurssin nimiP" link
    Then user is at the page which title is "Kurssin nimiP" and contain Delete button

  Scenario: user can remove course
    Given user is at the "courses" page
    When user clicks "Kurssin nimiP" link
    And user clicks Delete button
    Then the entry with title "Kurssin nimiP" is deleted
