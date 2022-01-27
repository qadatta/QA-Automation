package org.selenide.examples.cucumber.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ReadCsvFileExample {


public static void main(String[] args) {
	

	long recordNumber = getSucessRecordNumber("Status");

	printSuccessRecordDetails(recordNumber);
}

private static void printSuccessRecordDetails(long recordNumber) {
	try {
		String fileName = "success-search-example.csv";
		File resourcesDirectory = new File("src/test/resources");

		Reader reader = Files.newBufferedReader(Paths.get(resourcesDirectory.getAbsolutePath() + "/upload_files/" + fileName));
		
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country","Status").withDelimiter(',').parse(reader);  
		int currentRecord = 0 ;
		for (CSVRecord csvRecord : records) {
			currentRecord++;
			if(recordNumber==currentRecord)
			{
				String name = csvRecord.get("Name");
				String email = csvRecord.get("Email");
				String phone = csvRecord.get("Phone");
				String country = csvRecord.get("Country");
				String Status = csvRecord.get("Status");

				System.out.printf("Name: %s \n",name);
				System.out.printf("email: %s \n",email);
				System.out.printf("phone: %s \n",phone);
				System.out.printf("country: %s \n",country);
				System.out.printf("Status: %s \n",Status);
				reader.close();
				break;
			}
		}
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

private static long getSucessRecordNumber(String columnName) {

	try {
		String fileName = "success-search-example.csv";
		File resourcesDirectory = new File("src/test/resources");

		Reader reader = Files.newBufferedReader(Paths.get(resourcesDirectory.getAbsolutePath() + "/upload_files/" + fileName));
		
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Name", "Email", "Phone", "Country","Status").withDelimiter(',').parse(reader);  
		long recordNumber = 0;
		for (CSVRecord csvRecord : records) {
			recordNumber++;
			String Status = csvRecord.get(columnName);
			if(Status.equalsIgnoreCase("Success"))
			{
				recordNumber = csvRecord.getRecordNumber();
				reader.close();
				return recordNumber;
			}	
		}
		reader.close();
		return 0;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
	
}


}
