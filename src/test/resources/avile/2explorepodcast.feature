Feature: user can explore podcast recommendations

  Scenario: user can find a page where user can explore podcast recommendations
    Given user is at the "home" page
    When user navigates to "Recommendations"
    Then Recommendations are shown

  Scenario: user can open a selected book recommendation
    Given user is at the "recommendations" page
    When user clicks "TestiPodcast" link
    Then user is at the page which title is "TestiPodcast"