Feature: File download test

  Scenario: user can download json file from website skip fields from json

    Given user is on json file download page
    When user click on download button
    Then file is downloaded
    And file matches with expected file "expected_file.json" skip below fields
    | *.token |
    | *.email |
    | *.updatedAt|
    | *.createdAt|


  Scenario: user can download json file from website

    Given user is on json file download page
    When user click on download button
    Then file is downloaded
    And file matches with expected file "expected_file2.json"

  Scenario: user can download xml file from website

    Given user is on xml file download page
    When user click on download button
    Then file is downloaded


