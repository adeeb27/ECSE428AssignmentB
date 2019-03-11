# Created by adeeb27 at 2019-02-28
Feature: Email

#  Scenario Outline: Normal Flow
#
#    Given I am logged in
#    And I am on the Gmail main page
#    When I compose an email to <email>
#    And I enter a <subject> and <body>
#    And I attach a file <attachment>
#    And I "Send" the email
#    Then the email should be sent with <subject> and <attachment>
#
#    Examples:
#      | email                         | attachment                                    | subject      | body       |
#      | testassignmentb428@gmail.com  | /Users/adeeb27/Downloads/attachments/preview16.png | First Test   | First Test |
#      | djadeeb@gmail.com             | /Users/adeeb27/Downloads/attachments/preview17.png  | Second Test  | Second Test |
#      | adeeb.amjad@mail.mcgill.ca    | /Users/adeeb27/Downloads/attachments/preview18.png | Third Test  | Third Test |
#      | dibbo.ritwik@gmail.com        | /Users/adeeb27/Downloads/attachments/preview19.png  | Fourth Test  | Fourth Test |
#      | intezhoha@gmail.com           | /Users/adeeb27/Downloads/attachments/Appa_eating_hay.gif | Fifth Test  | Fifth Test |
#
#    Scenario: Error Flow
#    #Invalid email email addresses, an example is address without "@" sign
#      Given I am logged in
#      And I am on the Gmail main page
#      When I compose an email to "hello"
#      And I enter in the subject box "Error Test"
#      And I "Send" the email
#      Then I should get an error

  Scenario: Alternate Flow
    #Invalid email email addresses, an example is address without "@" sign
    Given I am logged in
    And I am on the Gmail main page
    When I compose an email to "adeeb.amjad@mail.mcgill.ca"
    And I attach a file "/Users/adeeb27/Downloads/attachments/Appa_eating_hay.gif"
    And I "Send" the email
    Then the email should be sent with "/Users/adeeb27/Downloads/attachments/Appa_eating_hay.gif"


