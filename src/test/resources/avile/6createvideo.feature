Feature: user can create a new video recommendation

  Scenario: user can find a page where user can create new recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    Then Recommendations are shown

  Scenario: user can open a create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    Then the form "createVideoForm" is opened

  Scenario: user can set all fields in create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    And user clicks "Video" link
    And the entry "TestVideo" is entered into the field "inputVideoTitle"
    And the entry "TestVideoAuthor" is entered into the field "inputVideoAuthor"
    And the entry "http://google.com" is entered into the field "inputVideoUrl"
    And the form "createVideoForm" is submitted
    Then the entry with title "TestVideo" is added

