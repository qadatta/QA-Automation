package org.selenide.examples.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.skyscreamer.jsonassert.comparator.JSONComparator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileDownloadStepDefinitions {

  File jsonFile ;

  @Given("user is on json file download page")
  public void user_is_on_json_file_download_page() {
    open("https://www.appsloveworld.com/download-sample-json-file-with-multiple-records/");
   }

  @When("user click on download button")
  public void user_click_on_download_button() {
    System.out.println("Downloading file...");

    Configuration.downloadsFolder = "target/downloads";
    try {
    //   jsonFile =  $(By.xpath("//h3 [contains(.,'student')]/following::a[1]")).download();
      jsonFile =  $(By.cssSelector("a.Downloadbutton")).download();
    } catch (FileNotFoundException e) {
      System.out.println("Problem in file download");
    }
    System.out.println("File downloaded successfully");
  }

  @Then("file is downloaded")
  public void file_is_downloaded() {
    try {
      Scanner scan = new Scanner(jsonFile);
      System.out.println("Following is downloaded files...");
      while(scan.hasNextLine()){
        String line = scan.nextLine();
   //     System.out.println(line);
      }
      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println("Problem in opening  download file");
    } finally {
    }
  }

  @Given("user is on xml file download page")
  public void userIsOnXmlFileDownloadPage() {
    open("https://www.appsloveworld.com/download-free-sample-xml-file-with-multiple-records/");
  }

  @And("file matches with expected file {string}")
  public void fileMatchesWithExpectedFile(String fileName) {
    File expectedFile = new File(fileName);
    try {
      boolean fileContentMatch = FileUtils.contentEquals(jsonFile, expectedFile);

      Assert.assertTrue("FIle contents not matched",fileContentMatch);


    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  @And("file matches with expected file {string} skip below fields")
  public void fileMatchesWithExpectedFileSkipBelowFields(String fileName,List<String> jsonPaths) {

    File expectedFile = new File(fileName);
    List<Customization> customizations = new ArrayList<>();

    for (String s: jsonPaths) {
      customizations.add(new Customization(s,(o1,o2)-> true));
    }

    try {
      String expectedJosn = FileUtils.readFileToString(expectedFile, "UTF-8");
      String actualJsonFile = FileUtils.readFileToString(jsonFile, "UTF-8");

      JSONComparator com = new CustomComparator(JSONCompareMode.STRICT, customizations.toArray(new Customization[0]));

//      com = new CustomComparator(JSONCompareMode.STRICT,
//                new Customization("*.updatedAt", (o1, o2) -> true));


      for (String s:jsonPaths) {

        System.out.println(s);
      }


      JSONAssert.assertEquals(actualJsonFile, expectedJosn, com);



    } catch (IOException | JSONException e) {
      e.printStackTrace();
    }


  }
}
