Feature: soap service using template for payloads
 
Scenario: Execute soap service by preparing request payload from dynamic file template with dynamic values

    Given user with soap service endpoint
    When user prepare request payload from template "template-01.xml" using below values
      | xmlpath	| 		nodeValue							|
      | catalog/book[@id='bk102']/price 					| 		18.99							|
      | catalog/book[@id='bk111']/price 					| 		299.99							|
    And submitted request
    Then response is updated with correct values
        | Attribute											| 		AttributeValue					|
        | price1 											| 		18.99							|
        | price2 											| 		199.99							|
        | price3 											| 		99.99							|
  
  
 Scenario: Execute soap service by preparing request payload from dynamic file template with dynamic values

    Given user with soap service endpoint
    When user prepare request payload from template "template-02.xml" using below values
      | xmlpath	| 		nodeValue							|
      | Envelope/Body/Add/intA			 					| 		77							|
      | Envelope/Body/Add/intB			 					| 		33							|
    And submitted request
    Then response is updated with correct values
        | Attribute											| 		AttributeValue				|
        | intA 												| 		77							|
        | intB 												| 		33							|
   
   
   
   Scenario: Execute soap service by preparing request payload from dynamic file template with dynamic values

    Given user with soap service endpoint
    When user prepare request payload from template "template-02.xml" using below values
      | xmlpath	| 		nodeValue							|
      | Envelope/Body/Add/intA			 					| 		499							|
      | Envelope/Body/Add/intB			 					| 		533							|
    And submitted request
    Then response is updated with correct values
        | Attribute											| 		AttributeValue				|
        | intA 												| 		299							|
        | intB 												| 		333							|
   