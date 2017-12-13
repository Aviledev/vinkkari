Feature: user can create a new blogpost recommendation

  Scenario: user can open a create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    And user clicks Blogpost button
    Then the form "createBlogpostForm" is opened

  Scenario: user can set all fields in create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    And user clicks Blogpost button
    And the entry "TestBlogpost" is entered into the field "inputBlogpostTitle"
    And the entry "TestBlogpostAuthor" is entered into the field "inputBlogpostAuthor"
    And the entry "https://blogbook.fi" is entered into the field "inputBlogpostUrl"
    And the form "createBlogpostForm" is submitted
    Then the entry with title "TestBlogpost" is added

  Scenario: users input is validated
    Given user is at the "recommendations" page
    When user clicks Create button
    And user clicks Blogpost button
    And the entry "" is entered into the field "inputBlogpostTitle"
    And the entry "TestBlogpostAuthor" is entered into the field "inputBlogpostAuthor"
    And the entry "https://blogbook.fi" is entered into the field "inputBlogpostUrl"
    And the form "createBlogpostForm" is submitted
    Then error message "may not be empty" is given
