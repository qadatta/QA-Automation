Feature: Login Functionality 

	In order to do internet banking
	As a valid Para Bank customer
	I want to login successfully

## Scenario Outline Example - 1
#@Login
#Scenario Outline: Login Successful
#
#When User details api executed
#Given I am in the login page of the Para Bank Application
#When I enter valid <username> and <password>
#Then I should be taken to the Overview page
#
#Examples:
#|username|password|
#|"dmore1"|"test123"|


@googleSearch
Scenario: Google search using api response

When User details api executed - new
Given I am on google home page
When google search using api response value
Then verify api response in result page


## Scenario Outline Example - 1
#@Login
#Scenario Outline: Login Successful
#
#Given I am in the login page of the Para Bank Application
#When I enter valid <username> and <password>
#Then I should be taken to the Overview page
#
#Examples:
#|username|password|
#|"dmore"|"test123"|
#|"tautester"|"password"|

## Scenario Outline Example - 2
#@Login
#Scenario Outline: Login Successful
#
#Given I am in the login page of the Para Bank Application
#When I enter valid <username> and <password> with <userFullName>
#Then I should be taken to the Overview page
#
#Examples:
#|username|password|userFullName|
#|"autotester"|"password"|"Auto Tester"|
#|"tautester"|"password"|"TAU Tester"|
#
## Scenario for Inline Parameters
#Scenario: Login Successful - Inline Parameters
#
#Given I am in the login page of the Para Bank Application
#When I enter valid credentials
#|autotester|password|
#Then I should be taken to the Overview page