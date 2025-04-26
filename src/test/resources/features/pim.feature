Feature: PIM - Employee Management

  Background: # This step is going to be executed before every scenario
    Given I am on the OrangeHRM login page
    When User enters valid credentials
      | username | password |
      | Admin    | admin123 |
    And I navigate to the PIM page

  Scenario Outline: Add a new employee
    When I click on the Addpim button
    And I enter employee details "<firstName>" "<lastName>" "<employeeID>"
    And I click the Save button and get successful message
    And the new employee should be listed in the system "<employeeID>"

    Examples:
      | firstName | lastName | employeeID |
      | Borat12     | Test     | 96322    |
      | MaybeTest   | Mayb     | 6322    |

    Scenario Outline: Edit Employee Details
      When I search for existing employee "<firstName>" "<lastName>" "<employeeID>"
      Then I click edit modify details and save changes  "<employeeID>"
      And I should see a message appear if any of the field are incomplete.

      Examples:
        | firstName | lastName | employeeID |
        | Borat12     | Test     | 96322    |

  Scenario Outline: Deleting an Employee
    When I search for existing employee "<firstName>" "<lastName>" "<employeeID>"
    And I select the employee checkbox "<employeeID>".
    And I click delete button and confirm deletion "<employeeID>".
    Then I should see a success message

    Examples:
      | firstName | lastName | employeeID |
      | Borat12     | Test     | 96322    |

  Scenario Outline: Searching for an Employee
    When I enter employee fist,last,employeeid "<firstName>" "<employeeID>"
    And I click search button.
    Then I should see that employee is removed from the list.

    Examples:
      | firstName | lastName | employeeID |
      | Borat12     | Test     | 96322    |
      | MaybeTest   | Mayb     | 6322    |