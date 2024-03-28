Feature: Get User
  Checking all the secnarios in get user api

  Scenario: Get newly created user
    Given Get the user details of newly created user
    And Vailed 200 response and user details

  Scenario: Get the deleted or invaild token
    Given Give already deleted user tokens to get
    Then Vaild 401 code for get

  Scenario: Give the request without headers
    Given Request without headers get
    Then Vaild the response of request without headers get
