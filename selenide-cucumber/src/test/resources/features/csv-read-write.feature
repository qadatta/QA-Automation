Feature: Test csv (comma /pipe sseperated ) file read write
 
@csv_example
Scenario: read csv file and update with given values

    Given user read and print csv file "users.csv"
    When user update "users.csv" using below values
      | row_identifier										| 		columnName							| value_to_update |
      | Name:Satish,Email:satish@example.com				| 		Country								| Test update 11	  |
      | Name:Dan,Email:dan@example.com						| 		Country								| Test update 21	  |
 
    Then user read and print csv file "users.csv"

@csv_example
Scenario: read csv file and update with column value for given record number

    Given user read and print csv file "users1.csv"
    When user update "users1.csv" using below values row and column details
      | row_number											| 		columnName							| columnValue 		|
      | 4													| 		Country								| Test country2 		|
      | 2													| 		Email								| Test2@example.com	|
      
    Then user read and print csv file "users1.csv"


@csv_example
Scenario: read csv file and update with column value for given record number

    Given user read and print csv file "users_PipeSeperated.txt"
    When user update "users_PipeSeperated.txt" using below values row and column details
      | row_number											| 		columnName							| columnValue 		|
      | 4													| 		Country								| Test country211 		|
      | 2													| 		Email								| Test21@example.com	|
      
    Then user read and print csv file "users_PipeSeperated.txt"


