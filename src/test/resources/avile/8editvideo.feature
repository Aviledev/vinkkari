Feature: user can edit a video recommendation

  Scenario: user can find a page where user can edit a recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    And user clicks "TestVideo" link
    Then user is at the page which title is "TestVideo" and contain Edit button

  Scenario: user can open edit form
    Given user is at the "recommendations" page
    When user clicks "TestVideo" link
    And user clicks Edit button
    Then the form "editVideoForm" is opened

  Scenario: user can edit all fields in edit form
    Given user is at the "recommendations" page
    When user clicks "TestVideo" link
    And user clicks Edit button
    And the entry "P" is entered into the field "inputVideoTitle"
    And the entry "P" is entered into the field "inputVideoAuthor"
    And the entry "P" is entered into the field "inputVideoURL"
    And user clicks Save button
    Then the entry with title "TestVideoP", author "TestVideoAuthorP" and url "http://google.comP" is saved

