Feature: user can explore book recommendations

  Scenario: user can find a page where user can explore book recommendations
    Given user is at the "home" page
    When user navigates to "Recommendations"
    Then Recommendations are shown

  Scenario: user can open a selected book recommendation
    Given user is at the "books" page
    When user clicks "TestiKirja" link
    Then user is at the page which title is "TestiKirja"
