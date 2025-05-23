Feature: Admin Page - User Management

  Background:
    Given I am on the OrangeHRM login page
    When User enters valid credentials
      | username | password |
      | Admin    | admin123 |
    And User navigates to the Admin User Management page

  Scenario Outline: Add a new system user
    When I click on the Add button
    And I enter user details "<userRole>" "<userName>" "<employeeName>" "<status>" "<password>"
    And I click the Save button
    Then I should see a success message
    And the new user should be listed with following information "<userRole>" "<userName>" "<employeeName>" "<status>" "<password>"
    Examples:
      | userRole | userName        | employeeName         | status  | password |
      | ESS      | adminPageTest11 | Kate Jane Austen     | Enables | admin321 |
      | Admin    | adminPageTest21 | Timothy Lewis Amiano | Enables | admin456 |

  Scenario Outline: Edit an existing system user
    When I click edit for user "<username>"
    And I modify the user details "<status>" "<userRole>"
    And the new user should be listed with following information "<userRole>" "<userName>" "<employeeName>" "<status>"
    Examples:
      | userRole | userName        | employeeName         | status  |
      | ESS      | adminPageTest11 | Kate Jane Austen     | Enables |
      | Admin    | adminPageTest21 | Timothy Lewis Amiano | Enables |