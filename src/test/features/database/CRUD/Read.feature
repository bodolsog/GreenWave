Feature: Read
  As a App User
  I want read crosses from database
  So that effect appear in database

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
