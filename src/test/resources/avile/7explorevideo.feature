Feature: user can explore video recommendations

  Scenario: user can find a page where user can explore video recommendations
    Given user is at the "home" page
    When user navigates to "Recommendations"
    Then Recommendations are shown

  Scenario: user can open a selected video recommendation
    Given user is at the "recommendations" page
    When user clicks "TestVideo" link
    Then user is at the page which title is "TestVideo"
