Feature: user can remove a new video recommendation

  Scenario: user can find a page where user can remove a selected recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    And user clicks "TestVideoP" link
    Then user is at the page which title is "TestVideoP" and contain Delete button

  Scenario: user can remove recommendation
    Given user is at the "recommendations" page
    When user clicks "TestVideoP" link
    And user clicks Delete button
    Then the entry with title "TestVideoP" is deleted
