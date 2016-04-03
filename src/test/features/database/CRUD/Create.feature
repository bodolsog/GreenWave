Feature: Create
  As a App User
  I want add crosses to database
  So that effect appear in database

  @db
  Scenario Outline: Adding cross to database
    Given the database with <init_state> cross(es)
    When the App User add the cross
    Then the database have <end_state> cross(es)
    Examples:
      | init_state | end_state |
      | 0          | 1         |
      | 1          | 2         |
      | 5          | 6         |
      | 10         | 11        |

  @db
  Scenario: Adding multiple crosses to database
    Given the database with 2 cross(es)
    When the App User add the cross
     And the App User add the cross
    Then the database have 4 cross(es)