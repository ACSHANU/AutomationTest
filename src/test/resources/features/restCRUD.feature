@REST_Test
Feature: Rest CRUD Operations with Validation
  To validate rest Calls

  @REST_CREATE
  Scenario: Validation of POST Call - CREATE
  When  Post Data to Create a User
  Then Status Code should be 201
  And Response should contain "id"
  And Response should contain "createdAt"


  @REST_UPDATE
  Scenario: Validation of PUT Call - UPDATE
  When Put Data to Update a user with "John"  and  "Tech Architect" with ID as "2"
  Then Status code should be 200
  And Response should contain "updatedAt"

  @REST_READ
  Scenario: Validation of GET call - READ
When Data is retrieved for ID "2"
Then Status code should be 200


@REST_DELETE
Scenario: Validation of DELETE call - DELETE
When Data is deleted for ID "2"
Then Status code should be 204
