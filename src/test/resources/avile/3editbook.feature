Feature: user can create a new book recommendation

  Scenario: user can find a page where user can edit a recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    And user clicks "TestiKirja" link
    Then user is at the page which title is "TestiKirja" and contain Edit button

  Scenario: user can open edit form
    Given user is at the "recommendations" page
    When user clicks "TestiKirja" link
    And user clicks Edit button
    Then the form "editBookForm" is opened

  Scenario: user can edit all fields in edit form
    Given user is at the "recommendations" page
    When user clicks "TestiKirja" link
    And user clicks Edit button
    And the entry "M" is entered into the field "inputBookTitle"
    And the entry "M" is entered into the field "inputBookAuthor"
    And the entry "M" is entered into the field "inputBookIsbn"
    And user clicks Save button
    Then the entry with title "TestiKirjaM", author "TestiAuthorM" and isbn "TestiIsbnM" is saved

