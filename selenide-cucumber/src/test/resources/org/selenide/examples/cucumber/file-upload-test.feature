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


