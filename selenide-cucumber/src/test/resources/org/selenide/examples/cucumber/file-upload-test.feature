Feature: File upload test

Scenario: user can website and upload file

Given user is on file upload page
When user select file "hello_world.txt" to upload
And enter "test note" in input field
And click "Press"button to upload file
Then "test note1" visible on file upload result


Scenario: user can website and upload file and insert note dynamic from soap service

Given user executed soap service and set required veriables for scenario
And user is on file upload page
When user select file "hello_world.txt" to upload
And enter note parsed from api response into input field
And click "Press"button to upload file
Then note from soap service response is visible on file upload result


  Scenario: user can open website and upload file and insert note dynamic from soap service using input file for request payload

    Given user executed soap service request payload using file and set required variable for scenario
    And user is on file upload page
    When user select file "hello_world.txt" to upload
    And enter note parsed from api response into input field
    And click "Press"button to upload file
    Then note from soap service response is visible on file upload result



Scenario: Execute soap service by preparing request payload from file template with dynamic values

    Given user has soap service request for file upload
    When user prepare request by updating following values in template
      | xmlpath													 																				| 		nodeValue							|
      | catalog/book[@id='bk102']/price 																				| 		8.99							|
      | catalog/book[@id='bk111']/price 																				| 		99.99							|
    And submitted request with updated payload 
    Then response has updated values 