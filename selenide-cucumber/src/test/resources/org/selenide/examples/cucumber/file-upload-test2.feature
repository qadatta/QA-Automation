Feature: File upload test
Scenario: user can website and upload file

Given user executed soap service and set required veriables for scenario
And user is on file upload page
When user select file "hello_world.txt" to upload
And enter "test note" in input field
And click "Press"button to upload file
Then "test note" visible on file upload result
