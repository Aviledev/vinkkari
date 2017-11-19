Feature: user can create a new book recommendation

  Scenario: user can find a page where user can create new recommendation
    Given user is at the home page
    When user navigates to "Recommendations"
    Then "Recommendations" are shown
