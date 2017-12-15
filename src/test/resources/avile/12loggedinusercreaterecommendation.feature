Feature: logged in user can create a new blogpost recommendation

  Scenario: user can open a create dialog
    Given user is at the "login" page
    And the entry "admin@admin" is entered into the field "inputEmail"
    And the entry "123456" is entered into the field "inputPassword"
    And  user clicks Login button
    When user is at the "recommendations" page
    And user clicks Create button
    And  user clicks Blogpost button
    Then the form "createBlogpostForm" is opened

  Scenario: user can set all fields in create dialog
    Given user is at the "login" page
    And the entry "admin@admin" is entered into the field "inputEmail"
    And the entry "123456" is entered into the field "inputPassword"
    And  user clicks Login button
    When user is at the "recommendations" page
    And user clicks Create button
    And  user clicks Blogpost button
    And the entry "TestBlogpos" is entered into the field "inputBlogpostTitle"
    And the entry "TestBlogpostAutho" is entered into the field "inputBlogpostAuthor"
    And the entry "https://blogbook.f" is entered into the field "inputBlogpostUrl"
    And the form "createBlogpostForm" is submitted
    And the entry with title "TestBlogpos" is created by "Teppo Testaaja"