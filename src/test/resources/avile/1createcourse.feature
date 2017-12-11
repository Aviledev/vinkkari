Feature: user can create a new course

  Scenario: user can find a page where user can create new course
    Given user is at the "home" page
    When user navigates to "Courses"
    Then Courses are shown

  Scenario: user can open a create dialog
    Given user is at the "courses" page
    When user clicks Create button
    Then the form "createCourseForm" is opened

  Scenario: user can set all fields in create dialog
    Given user is at the "courses" page
    When user clicks Create button
    And the entry "Kurssin nimi" is entered into the field "inputCourseName"
    And the entry "TKTL22093" is entered into the field "inputCourseCode"
    And the entry "kuvaus" is entered into the field "inputCourseDescription"
    And the form "createCourseForm" is submitted
    Then the entry with title "Kurssin nimi" is added

