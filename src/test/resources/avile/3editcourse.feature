Feature: user can edit course

  Scenario: user can find a page where user can edit a course
    Given user is at the "home" page
    When user navigates to "Courses"
    And user clicks "Kurssin nimi" link
    Then user is at the page which title is "Kurssin nimi" and contain Edit button

  Scenario: user can open edit form
    Given user is at the "courses" page
    When user clicks "Kurssin nimi" link
    And user clicks Edit button
    Then the form "editCourseForm" is opened

  Scenario: user can edit all fields in edit form
    Given user is at the "courses" page
    When user clicks "Kurssin nimi" link
    And user clicks Edit button
    And the entry "P" is entered into the field "inputCourseName"
    And the entry "P" is entered into the field "inputCourseCode"
    And the entry "P" is entered into the field "inputCourseDescription"
    And user clicks Save button
    Then the entry with title "Kurssin nimiP", code "TKTL22093P" and decsription "kuvausP" is saved

