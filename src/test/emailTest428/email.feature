# Created by adeeb27 at 2019-02-28
Feature: Email
  # Enter feature description here

  Scenario Outline: Normal Flow

    Given I am logged in
    And I am on the Gmail main page
    When I press "Compose"
    And I compose an email to <email>
    And I enter a <subject> and <body>
    And I attach an <attachment>
    And I press "Send"
    Then the email should be sent with <subject> and <attachment>

    Examples:
      | email                          | attachment                                    | subject      | body       |
      | testassignmentb428@gmail.com   | /Users/adeeb27/Downloads/Appa_eating_hay.gif  | First Test   | First Test |