# Created by adeeb27 at 2019-02-28
Feature: Email
  # Enter feature description here

  Scenario: Login
    # Enter steps here
    Given I am logged in
    And I am on the Gmail main page
    When I press "Compose"
    And I compose an email to "testassignmentb428@gmail.com"
    And I attach a picture
    And I press "Send"
    Then the email should be sent