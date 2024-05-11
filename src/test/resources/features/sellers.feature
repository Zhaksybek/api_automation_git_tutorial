@api @seller
Feature: Test Sellers


  Scenario: get all sellers and their phone number
    Given user hits get all sellers api with "/api/myaccount/sellers" and parameters: isArchived "false", page 1, size 10
    Then user gets all sellers phone number


 @seller
    Scenario: Create single Seller
      Given user created new Seller with Faker class and api path "/api/myaccount/sellers"
      When get same seller by api path "/api/myaccount/sellers" id and validate name and phone number are matching
      Then delete same seller by api path "/api/myaccount/sellers" and id