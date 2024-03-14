Feature: Create User
  Checking all the secnarios in create user api

  Scenario: Create new user
    Given User name, mail and password and send the request
    Then Vailded the response

@tt
  Scenario: Try to create already existing user
    Given Give already existing user mail in body
    Then Vaild 400 and user already exists message.
@tt
  Scenario Outline: Give different methods to the create user call
    Given Different method like <methods> to the call
    Then Vaild the 404 in html response.

    Examples: 
      | methods |
      | Get     |
      | Put     |
      | Delete  |
@tt
  Scenario: Give empty body
    Given In body give empty body.
    Then Vaild the user vaildation fail response

  Scenario: Give the request without headers
    Given Request without headers
    Then Vaild the response of request without headers.
