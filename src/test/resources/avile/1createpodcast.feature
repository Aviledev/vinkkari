Feature: a user can create a new podcast recommendation

  Scenario: user can open a create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    Then the form "createPodcastForm" is opened

  Scenario: user can set all fields in create dialog
    Given user is at the "recommendations" page
    When user clicks Create button
    And user clicks Podcast button
    And the entry "TestiPodcast" is entered into the field "inputPodcastTitle"
    And the entry "TestiName" is entered into the field "inputPodcastName"
    And the entry "TestiAuthor" is entered into the field "inputPodcastAuthor"
    And the entry "https://www.podcast.com" is entered into the field "inputPodcastUrl"
    And the form "createPodcastForm" is submitted
    Then the entry with title "TestiPodcast" is added

  Scenario: user input is validated
    Given user is at the "recommendations" page
    When user clicks Create button
    And user clicks Podcast button
    And the entry "" is entered into the field "inputPodcastTitle"
    And the entry "TestiName" is entered into the field "inputPodcastName"
    And the entry "TestiAuthor" is entered into the field "inputPodcastAuthor"
    And the entry "https://www.podcast.com" is entered into the field "inputPodcastUrl"
    And the form "createPodcastForm" is submitted
    Then error message "may not be empty" is given

  Scenario: invalid url is not accepted
    Given user is at the "recommendations" page
    When user clicks Create button
    And user clicks Podcast button
    And the entry "TestiPodcast" is entered into the field "inputPodcastTitle"
    And the entry "TestiName" is entered into the field "inputPodcastName"
    And the entry "TestiAuthor" is entered into the field "inputPodcastAuthor"
    And the entry "failure" is entered into the field "inputPodcastUrl"
    And the form "createPodcastForm" is submitted
    Then error message "must be a valid URL" is given
