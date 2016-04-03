Feature: Delete
  As a App User
  I want delete crosses from database
  So that effect appear in database

  @db
  Scenario Outline: Delete cross from database
    Given the database with <init_state> cross(es)
    When the App User delete the cross with id <id>
    Then the database have <end_state> cross(es)
    Examples:
      | init_state | id | end_state |
      | 1          | 0  | 0         |
      | 3          | 0  | 2         |
      | 3          | 1  | 2         |
      | 3          | 2  | 2         |
      | 5          | 0  | 4         |
      | 10         | 0  | 9         |


  @db
  Scenario: Delete multiple crosses from database
    Given the database with 5 cross(es)
    When the App User delete the cross with id 3
     And the App User delete the cross with id 1
    Then the database have 3 cross(es)
