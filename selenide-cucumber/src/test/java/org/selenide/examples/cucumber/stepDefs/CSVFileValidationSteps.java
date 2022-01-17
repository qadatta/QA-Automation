package org.selenide.examples.cucumber.stepDefs;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.assertj.core.api.SoftAssertions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CSVFileValidationSteps {

	public static Scenario scenario;
	public static SoftAssertions softly ;
	public HashMap<String, String> scenarioValidations = new HashMap<>();
	@Before
	public void setUp(Scenario scenario) {
		this.scenario = scenario;
		 softly = new SoftAssertions();

	}

	@Given("user read and print csv file {string}")
	public void user_read_and_print_csv_file(String fileName) {
		scenario.log("record validated <span>successfully</span>");

		try {
			File resourcesDirectory = new File("src/test/resources");
			File usersCSVFile = new File(resourcesDirectory.getAbsolutePath() + "/upload_files/" + fileName);

			Reader reader = Files
					.newBufferedReader(Paths.get(resourcesDirectory.getAbsolutePath() + "/upload_files/" + fileName));

			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country").parse(reader);  //FOr file with | separator
//			Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country").withDelimiter('|').parse(reader);  //FOr file with | seperator

			
			for (CSVRecord csvRecord : records) {
				// Accessing Values by Column Index
				String name = csvRecord.get("Name");
				String email = csvRecord.get("Email");
				String phone = csvRecord.get("Phone");
				String country = csvRecord.get("Country");

				System.out.println("Record No - " + csvRecord.getRecordNumber());
				System.out.println("---------------");
				System.out.println("Name : " + name);
				System.out.println("Email : " + email);
				System.out.println("Phone : " + phone);
				System.out.println("Country : " + country);
				System.out.println("---------------\n\n");
			}

			reader.close();
			System.out.println("Sleeping for 2 sec");
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("user update {string} using below values")
	public void user_update_using_below_values(String fileName, io.cucumber.datatable.DataTable dataTable) {

		File resourcesDirectory = new File("src/test/resources");
		File usersCSVFile = new File(resourcesDirectory.getAbsolutePath() + "/upload_files/" + fileName);

		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		
		for (Map<String, String> columns : rows) {
			// store.addBook(new Book(columns.get("title"), columns.get("author")));
			String colNames = columns.get("row_identifier");
			String [] recordFilter = colNames.split(",");
			String updateColumnName = columns.get("columnName");
			String updateColumnValue = columns.get("value_to_update");
			
			try {
				updateCsvFile(usersCSVFile, updateColumnName + ":" + updateColumnValue, recordFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
	}
	@When("user update {string} using below values row and column details")
	public void user_update_using_below_values_row_coulmn_values(String fileName, io.cucumber.datatable.DataTable dataTable) {

		//get resource directory path and create file object of input file
		File resourcesDirectory = new File("src/test/resources");
		File usersCSVFile = new File(resourcesDirectory.getAbsolutePath() + "/upload_files/" + fileName);

		//Read datatable input from feature file
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		
		//Iterate datatable for all rows 
		for (Map<String, String> columns : rows) {
			String rowNumber = columns.get("row_number");
			String updateColumnName = columns.get("columnName");
			String updateColumnValue = columns.get("columnValue");
			
			try {
				// Call update file method to update record in file
				//It will delete file and create similar file with updated record
				updateCsvFile(usersCSVFile, Integer.parseInt(rowNumber), updateColumnName, updateColumnValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
		
	}

	
	
	/**
	 * 
	 * @param f  FIle to update
	 * @param recordNumber  Record/row  number which need to be updated, Assumed 0th record is header
	 * @param columnName	Name of column in csv file
	 * @param columnValue	value which need to be updated for above column for input row number
	 * @throws Exception
	 */
	public void updateCsvFile(File f,int recordNumber, String columnName , String columnValue) throws Exception {
		CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country"));							//for comma seperated file
//		CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country").withDelimiter('|'));		//Pipe seperated file

		List<CSVRecord> list = parser.getRecords();
		String edited = f.getAbsolutePath();
		f.delete();
		
		CSVPrinter printer = new CSVPrinter(new FileWriter(edited),
				CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country").withDelimiter(','));
		
//		CSVPrinter printer = new CSVPrinter(new FileWriter(edited),
//				CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country").withDelimiter('|'));
		int rowNum = 1;
		for (CSVRecord record : list) {
			
			System.out.println("Record>>>>>> "+record);
			String[] s = toArray(record);
			
			if(rowNum == recordNumber)
			{
				if(columnName.equalsIgnoreCase("Name"))
					s[0] = columnValue;
				else if(columnName.equalsIgnoreCase("Email"))
					s[1] = columnValue;
				else if(columnName.equalsIgnoreCase("Phone"))
					s[2] = columnValue;
				else if(columnName.equalsIgnoreCase("Country"))
					s[3] = columnValue;
			}
				

			print(printer, s);
			rowNum++;
			
		}
		parser.close();
		printer.close();

		System.out.println("CSV file was updated successfully !!!");
	}

	/**
	 * return array of string from csv file record
	 * @param rec	
	 * @return
	 */
	public  String[] toArray(CSVRecord rec) {
		String[] arr = new String[rec.size()];
		int i = 0;
		for (String str : rec) {
			arr[i++] = str;
		}
		return arr;
	}

	public  void print(CSVPrinter printer, String[] s) throws Exception {
		for (String val : s) {
			printer.print(val != null ? String.valueOf(val) : "");
		}
		printer.println();
	}

	
	public void updateCsvFile(File f, String updateValue ,String ... recordFilter ) throws Exception {
		CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country"));							//for comma seperated file
//		CSVParser parser = new CSVParser(new FileReader(f), CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country").withDelimiter('|'));		//Pipe seperated file
		List<CSVRecord> list = parser.getRecords();
		String edited = f.getAbsolutePath();
		
		Map<String, String> filterRecordMap = new HashMap<>(); 
		
		
		for (int i = 0; i < recordFilter.length; i++) {
			String[] columnValue = recordFilter[i].split(":");
			filterRecordMap.put(columnValue[0], columnValue[1]);
		}
		
		f.delete();
		CSVPrinter printer = new CSVPrinter(new FileWriter(edited),
				CSVFormat.DEFAULT);
		for (CSVRecord record : list) {
			String name = record.get("Name");
			String email = record.get("Email");
			String phone = record.get("Phone");
			String country = record.get("Country");
			
			System.out.println("-----------------------");
			String[] s = toArray(record);
			
			boolean recordFound = true;
			for (Map.Entry<String, String> entry : filterRecordMap.entrySet()) {

				String inputKey = entry.getKey();
				String inputValue = entry.getValue();
				String csvValue = record.get(inputKey);

				if(false==csvValue.equalsIgnoreCase(inputValue)) {
					recordFound = false;
					break;
				}
				
			}
			
			// fetch update column name and value and then update in file
			String []updateAttr = updateValue.split(":");

			
			
			if(recordFound)
			{
				if(updateAttr[0].equalsIgnoreCase("Name"))
					s[0] = updateAttr[1];
				else if(updateAttr[0].equalsIgnoreCase("Email"))
					s[1] = updateAttr[1];
				else if(updateAttr[0].equalsIgnoreCase("Phone"))
					s[02] = updateAttr[1];
				else if(updateAttr[0].equalsIgnoreCase("Country"))
					s[3] = updateAttr[1];
			}
				

			print(printer, s);
			
		}
		parser.close();
		printer.close();

		System.out.println("CSV file was updated successfully !!!");
	}

	
}
