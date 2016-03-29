Feature: CRUD
  As a App User
  I want perform simple operations on database
  So that effect appear in database

  # Create
  @db
  Scenario Outline: Adding cross to database
    Given the database with <init_state> crosses
    When the App User add a <added_crosses>
    Then the database have <end_state> crosses
    Examples:
      | init_state | added_crosses | end_state |
      | 0          | 1             | 1         |

