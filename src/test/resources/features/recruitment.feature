Feature: Recruitment Module Feature
  Background: # This step is going to be executed before every scenario
    Given I am on the OrangeHRM login page
    When User enters valid credentials
      | username | password |
      | Admin    | admin123 |
    And I navigate to the Recruitment page

#    Scenario Outline: Adding a new Candidate
#      When I click Add Candidate
#      And I enter candidate details "<lastName>" "<firstName>" "<userVacancy>" "<userEmail>".
#      And I Click save and verify the candidate is added. "<firstName>"
#
#      Examples:
#        | lastName | firstName | userVacancy              | userEmail             |
#        | Tester   | ochin     | Junior Account Assistant | oching@gmail.com      |
#        | Tester2  | ochin2    | Junior Account Assistant | oching@gmail.com      |
#        | Actual   | NonDeleted| Junior Account Assistant | nondeleted@gmail.com  |
#
#
#  Scenario Outline: Editing Candidate Details
#  When I search for candidate "<lastName>" "<firstName>" "<userVacancy>".
#  And I click Edit, modify details, and save changes "<lastName>" "<modifiedLastName>" "<modifiedUserVacancy>".
#  Then I should see succes message is displayed.
#  And Details are updated correctly. "<modifiedLastName>"
#
#  Examples:
#    | lastName | firstName | userVacancy                | modifiedLastName | modifiedUserVacancy |
#    | Tester   | ochin     | Junior Account Assistant   | JORA             | Senior QA Lead      |
#    | Tester2  | ochin2    | Junior Account Assistant   | JORA4            | Senior QA Lead      |
#
#
#
#  Scenario Outline: Deleting an Employee
#    When I search for candidate "<lastName>" "<firstName>" "<userVacancy>".
#    And I select the employee checkbox delete to confirm deletion "<lastName>".
#    And I should see succes message is displayed for deletion.
#    Then I should see that employee is removed from the list "<lastName>".
#
#    Examples:
#      | lastName | firstName | userVacancy     |
#      | Tester   | JORA      | Senior QA Lead  |
#      | Tester2  | JORA4     | Senior QA Lead  |


  Scenario Outline: Searching for an Employee
    When I search for candidate "<firstName>".
    And I should see matching employee details appear "<firstName>" "<lastName>".
    Then I should see that employee is removed from the list "<firstName>".


    Examples:
      | lastName | firstName      | userVacancy              |
      | Actual   | NonDeleted           | Senior QA Lead           |
      | Tester2  | JORA4          | Senior QA Lead           |
      | Actual   | JORA    | Junior Account Assistant |