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
      | testassignmentb428@gmail.com   | /Users/adeeb27/Downloads/attachments/preview16.png | First Test   | First Test |
      | djadeeb@gmail.com             | /Users/adeeb27/Downloads/attachments/preview17.png  | Second Test  | Second Test |
      | adeeb.amjad@mail.mcgill.ca    | /Users/adeeb27/Downloads/attachments/preview18.png | Third Test  | Third Test |
      | dibbo.ritwik@gmail.com        | /Users/adeeb27/Downloads/attachments/preview19.png  | Fourth Test  | Fourth Test |
      | intezhoha@gmail.com           | /Users/adeeb27/Downloads/attachments/Appa_eating_hay.gif | Fifth Test  | Fifth Test |
