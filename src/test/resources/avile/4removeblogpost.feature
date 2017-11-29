Feature: user can remove a new blogpost recommendation

  Scenario: user can find a page where user can remove a selected recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    And user clicks "TestBlogpostP" link
    Then user is at the page which title is "TestBlogpostP" and contain Delete button

  Scenario: user can remove recommendation
    Given user is at the "recommendations" page
    When user clicks "TestBlogpostP" link
    And user clicks Delete button
    Then the entry with title "TestBlogpostP" is deleted
