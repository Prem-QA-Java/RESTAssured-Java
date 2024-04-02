Feature: Delete User
  Checking all the secnarios in delete user api

  Scenario: Delete newly created user
    Given Already exists user tokens in excel and taken then to delete user
    And Vailed 200 response
    And Try to get the user

  Scenario: Try deleting already deleted user
    Given Give already deleted user tokens
    Then Vaild 401 code
    And Try to get the user

  Scenario: Give the request without headers
    Given Request without headers delete
    Then Vaild the response of request without headers delete
