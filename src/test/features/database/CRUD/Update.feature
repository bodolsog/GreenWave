Feature: Update
  As a App User
  I want update crosses from database
  So that effect appear in database


  Background:
    Given the database with 5 cross(es)
    When the App User add the cross with latitude 12.121212 and longitude 52.5252525

  @db
  Scenario Outline: Update latitutde and longitude
    When the App User update the cross with new latitude <lat> and longitude <lng>
    Then updated Cross have latitude <lat> and longitude <lng>
    Examples:
      | lat      | lng       |
      | 24.12563 | 52.965298 |
