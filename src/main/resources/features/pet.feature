@Pet
Feature: Validate pets

  @petsPostAdd
  Scenario Outline: Validate POST add pet
    Given the following POST request adds a pet
    Then the status code is 200 for the POST pet add request
    And the body response of the POST request contains the "<id>" and "<name>" of the pet added
    Examples:
      | id   |   name   |
      | 1999 |   gato   |

  @petsPutUpdate
  Scenario Outline: Validate PUT update pet
    Given the following PUT request update a pet
    Then the status code is 200 for the PUT pet update request
    And the body response of the PUT request contains the "<id>" and "<name>" of the pet updated

    Examples:
      | id   |   name   |
      | 1999 |   Perro  |


  @petsGetByStatus
  Scenario Outline: Validate GET pet by status
    Given the following GET request that brings the pets by his status
    Then  the status code is 200 for the GET pets by status request
    And  the body response of the GET request contains the previously added pet with the status "<status>"

    Examples:
      | status    |
      | available |

  @petsGetByID
  Scenario Outline: Validate GET pet by ID
    Given the following GET request that brings the pets by his ID
    Then  the status code is 200 for the GET pets by ID request
    And the body response of the GET pet by ID request contains the "<id>" and "<name>" of the pet

    Examples:
      | id   |   name   |
      | 1999 |   Perro  |

  @petsDelete
  Scenario Outline: Validate DELETE pet
    Given the following DELETE request that deletes a pet by his ID
    And   the status code is 200 for the DELETE pets by ID request
    Then  the body response of the DELETE pet by ID request contains the "<id>" of the pet

    Examples:
      | id    |
      | 1999  |

