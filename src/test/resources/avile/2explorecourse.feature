Feature: user can explore courses

  Scenario: user can open a selected course
    Given user is at the "courses" page
    When user clicks "Kurssin nimi" link
    Then user is at the page which title is "Kurssin nimi"
