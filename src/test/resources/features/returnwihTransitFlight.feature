@Blibli_Travel
Feature: FLight Ticket Booking
  @Test1
  Scenario: User books the flight ticket
     Given The user should go to the home page of the blibli travel website
     Then Enter the details to book a flight
     And Click book a flight
     And Scroll the page until the flights are visible
    And click on the detail link and store details in map
    And Click on the select go button
   And Click on the detail section for return and verify the values
    And Click on the select go button for return
   Then Click on the select go button for return
    And Enter the details to proceed
    And Click on the detail section and verify
   And Click on the detail section for return
   And Click on continue ordering
   And Click on the detail section and verify the values
   And Click on the detail section and verify the values for return