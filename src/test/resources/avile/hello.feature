Feature: user can navigate to other page

  Scenario: user can find a searched reference
    Given user is at the main page
    When a link "Recommendations" is clicked
    Then "Title" is shown
