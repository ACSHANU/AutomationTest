@UI_Test
Feature: Purchasing products online

  User should be able to login to the website with correect credentials
  Purchase required products with validation.


  Background:
    Given User navigate to Login page
    When login using emailaddress as "tester@testers.com" and passwprd as "BJSSTest"
    Then Should be sucessfully Loggedin

    @UITest1
  Scenario: HAPPY PATH, PURCHASE 2 ITEMS
    Given User navigates to products page
    When User clicks on quickView of "Blouse"
    And Enter Quantity "3" and Add that item to your basket
    And Continue Shopping
    And User clicks on quickview of "Printed Dress"
    And Add item to your basket
    And Continue Shopping
    And View basket
    Then correct sizes should be selected
    And Prices should be as  expected
    And Total Products should be the sum of the two items
    And Total should be the sum of Total Products and Shopping
    And Complete Order by wire



      @UITest2
    Scenario: REVIEW PREVIOUS ORDERS AND ADD A MESSAGE
      Given User navigate to Order History
      And Select  item "2" from Orders
      When Add a comment "Test Comment"
      Then Comment should appear in Messages

        @UITest3
      Scenario: CAPTURE IMAGES
        Given User navigate to Order History
        When Select recent order
        Then Qty selected should be 10 for "Blouse"






