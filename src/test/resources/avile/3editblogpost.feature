Feature: user can edit a blogpost recommendation

  Scenario: user can find a page where user can edit a recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    And user clicks "TestBlogpost" link
    Then user is at the page which title is "TestBlogpost" and contain Edit button

  Scenario: user can open edit form
    Given user is at the "recommendations" page
    When user clicks "TestBlogpost" link
    And user clicks Edit button
    Then the form "editBlogpostForm" is opened

  Scenario: user can edit all fields in edit form
    Given user is at the "recommendations" page
    When user clicks "TestBlogpost" link
    And user clicks Edit button
    And the entry "P" is entered into the field "inputBlogpostTitle"
    And the entry "P" is entered into the field "inputBlogpostAuthor"
    And the entry "P" is entered into the field "inputBlogpostUrl"
    And user clicks Save button
    Then the entry with title "TestBlogpostP", author "TestBlogpostAuthorP" and url "https://blogbook.fiP" is saved

