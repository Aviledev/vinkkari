Feature: user can explore blogpost recommendations

  Scenario: user can find a page where user can explore blogpost recommendations
    Given user is at the "home" page
    When user navigates to "Recommendations"
    Then Recommendations are shown

  Scenario: user can open a selected blogpost recommendation
    Given user is at the "recommendations" page
    When user clicks "TestBlogpost" link
    Then user is at the page which title is "TestBlogpost"
