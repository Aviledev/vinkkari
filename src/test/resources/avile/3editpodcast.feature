Feature: user can edit podcast recommendation

  Scenario: user can find a page where user can edit a recommendation
    Given user is at the "home" page
    When user navigates to "Recommendations"
    And user clicks "TestiPodcast" link
    Then user is at the page which title is "TestiPodcast" and contain Edit button

  Scenario: user can open edit form
    Given user is at the "recommendations" page
    When user clicks "TestiPodcast" link
    And user clicks Edit button
    Then the form "editPodcastForm" is opened

  Scenario: user can edit all fields in edit form
    Given user is at the "recommendations" page
    When user clicks "TestiPodcast" link
    And user clicks Edit button
    And the entry "M" is entered into the field "inputPodcastTitle"
    And the entry "M" is entered into the field "inputPodcastName"
    And the entry "M" is entered into the field "inputPodcastAuthor"
    And user clicks Save button
    Then the entry with title "TestiPodcastM", name "TestiNameM" and author "TestiAuthorM" is saved