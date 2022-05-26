@Store
Feature: Validate store

  @storePostOrder
  Scenario Outline: Validate POST add order
    Given the following POST request that adds a order
    Then the status code is 200 for the POST order add request
    And the body response of the POST request contains the "<idOrder>" and "<idPet>" of the order added

    Examples:
      | idOrder          |   idPet   |
      | 11121999         |   1999    |

  @storeGetOrderByID
  Scenario Outline: Validate GET order by ID
    Given the following GET request that brings the order by his ID
    Then  the status code is 200 for the GET order by ID request
    And the body response of the GET order by ID request contains the "<idOrder>" and "<idPet>" of the order

    Examples:
      | idOrder          |   idPet   |
      | 11121999         |   1999    |

  @storeOrderDelete
  Scenario Outline: Validate DELETE order
    Given the following DELETE request that deletes a order by his ID
    Then   the status code is 200 for the DELETE order by ID request
    And  the body response of the DELETE order by ID request contains the "<id>" of the order

    Examples:
      | id           |
      | 11121999     |

  @storeGetInventory
  Scenario: Validate GET order inventory
    Given the following GET request that brings the order inventory
    Then  the status code is 200 for the GET order inventory request
    And   the body response of the GET order inventory request is not empty
