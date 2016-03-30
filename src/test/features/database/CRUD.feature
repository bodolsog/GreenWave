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

  # Update

  # Delete
  @db
  Scenario Outline: Delete cross from database
    Given the database with <init_state> cross(es)
    When the App User delete the cross
    Then the database have <end_state> cross(es)
    Examples:
      | init_state | end_state |
      | 1          | 0         |
      | 2          | 1         |
      | 5          | 4         |
      | 10         | 9         |
