Feature: user can create a new book recommendation

  Scenario: user can find a page where user can create new recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    Then Recommendations are shown

  Scenario: user can open a create dialog
    Given user is at the "books" page
    When user clicks Create button
    Then Recommendations are shown

  Scenario: user can set all fields in create dialog
    Given user is at the "books" page
    When user clicks Create button
    And the entry "TestiKirja" is entered into the field "title"
    And the entry "TestiAuthor" is entered into the field "author"
    And the entry "TestiIsbn" is entered into the field "isbn"
    And the form "createBookForm" is submitted
    Then the entry with title "TestiKirja" is added

