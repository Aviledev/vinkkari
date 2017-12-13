Feature: user can create a new book recommendation

  Scenario: user can find a page where user can create new recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    Then Recommendations are shown

  Scenario: user can open a create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    Then the form "createBookForm" is opened

  Scenario: user can set all fields in create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    And the entry "TestiKirja" is entered into the field "inputBookTitle"
    And the entry "TestiAuthor" is entered into the field "inputBookAuthor"
    And the entry "978-1540753809" is entered into the field "inputBookIsbn"
    And the form "createBookForm" is submitted
    Then the entry with title "TestiKirja" is added

  Scenario: user input is validated
    Given user is at the "recommendations" page
    When user clicks Create button
    And the entry "" is entered into the field "inputBookTitle"
    And the entry "TestiAuthor" is entered into the field "inputBookAuthor"
    And the entry "978-1540753809" is entered into the field "inputBookIsbn"
    And the form "createBookForm" is submitted
    Then error message "may not be empty" is given

  Scenario: wrong isbn is not accepted
    Given user is at the "recommendations" page
    When user clicks Create button
    And the entry "Testikirja" is entered into the field "inputBookTitle"
    And the entry "TestiAuthor" is entered into the field "inputBookAuthor"
    And the entry "978-1540753800" is entered into the field "inputBookIsbn"
    And the form "createBookForm" is submitted
    Then error message "Not valid ISBN" is given
