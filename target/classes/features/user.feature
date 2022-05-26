@User
Feature: Validate users

  @userPostCreateUserArray
  Scenario: Validate POST create user with array
    Given the following POST request that create using a array
    Then the status code is 200 for the POST request that create using a array
    And the body response for the POST request that create using a array contains the name 'message' with the value 'ok'

  @userPostCreateUserList
  Scenario: Validate POST create user with list
    Given the following POST request that create using a list
    Then the status code is 200 for the POST request that create using a list
    And the body response for the POST request that create using a list contains the name 'message' with the value 'ok'

  @userGetByName
  Scenario Outline: Validate GET user by name
    Given the following GET request that brings user by his name
    Then  the status code is 200 for the GET user by name request
    And the body response of the GET user by name request contains the "<name>" of the user

    Examples:
      | name     |
      | Roberto  |

  @userPutUpdate
  Scenario Outline: Validate PUT update user
    Given the following PUT request update a user
    Then the status code is 200 for the PUT user update request
    And the body response of the PUT request contains the "<id>" of the user updated

    Examples:
      | id   |
      | 1999 |

  @userDeleteByName
  Scenario Outline: Validate DELETE user
    Given the following DELETE request that deletes a user by his name
    Then  the status code is 200 for the DELEsE user by name request
    And the body response of the DELETE user by name request contains the "<name>" of the user

    Examples:
      | name     |
      | Roberto  |


  @userGetLogin
  Scenario: Validate get user login
    Given the following GET request logs the user
    Then  the status code is 200 for the GET log user request
    And the body response of the GET log user request contains contains the name 'message' with the value 'logged in user session'

  @userGetLogout
  Scenario: Validate get user log out
    Given the following GET request logout the user
    Then  the status code is 200 for the GET logout user request
    And  the body response of the GET logout user request contains contains the name 'message' with the value ok



