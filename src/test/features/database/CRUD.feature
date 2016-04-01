Feature: CRUD
  As a App User
  I want perform simple operations on database
  So that effect appear in database

  # Create
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


  # Read
  @db
  Scenario Outline: Read a cross from database
    Given the database with <init_state> cross(es)
    When the App User try to get cross with id <id>
    Then a cross with <id> is returned
    Examples:
      | init_state | id |
      | 1          | 0  |
      | 3          | 0  |
      | 3          | 1  |
      | 3          | 2  |

  @db
  Scenario: Read a cross, that not exist in database
    Given the database with 3 cross(es)
    When the App User try to get cross with id 5
    Then a null is returned


  # Update

  # Delete
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
