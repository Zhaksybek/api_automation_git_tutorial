
@bankAccount  @api
Feature: Test Bank Account


  Scenario Outline: Create many Bank Accounts
    Given Create "<bank_account_name>" and "<description>" and "<type_of_pay>" and <balance>
    And Get created Bank account by id and check status code is 200
    Then Get same Bank account by id and delete

    Examples:
      |bank_account_name |description    |type_of_pay|balance|
      | Random Bank2     |Financial LLC  | CASH      |  1000 |
      | Eagle Bank2      |Financial LLC  | BANK      |  1200 |
      | BAFO Bank1       | Financial LLC | CASH      |  900  |
      | CASO Bank1       |Financial LLC  | BANK      |  1300 |
      | OTL Bank1        |Financial LLC  | CASH      |  1000 |
      | GASOLINE Bank1   |Financial LLC  | BANK      | 2000  |