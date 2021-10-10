package org.selenide.examples;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

import java.io.File;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

public class FileUploadTest {

	String fileUploadNote = " Test ";

	@BeforeClass
	public static void setupAllureReports() {
		SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

		// or for fine-tuning:
		SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
				.screenshots(false)
				.savePageSource(true)
		);
	}

  @Test
  public void uploadFileUsingSelenideTest() {
	  sleep(10000);

		//	  System.setProperty("selenide.headless","true");
	  SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    open("http://demo.guru99.com/test/upload/");
	  File file = $("#uploadfile_0").uploadFile(new File("hello_world.txt"));
	  $("#submitbutton").click();
	    $("#res").shouldHave(text("successfully uploaded"));

  }

	/**
	 * Test to read soap service response and use parsed response in browser automation (Selenide)
	 */
	@Test
  public void uploadFileUsingSelenideTest2() {
  //	System.setProperty("selenide.headless","false");
		sleep(10000);

  	//Set variable using soap service response call
  	testTogetResultFromSoapServiceResponse();
	System.out.println("fileUploadNote>> "+ fileUploadNote);

    open("https://cgi-lib.berkeley.edu/ex/fup.html");
	  File file = $("input[name='upfile']").uploadFile(new File("hello_world.txt"));
	  $("input[name='note'").setValue(fileUploadNote);
	    $("input[value='Press'").click();

	    $("h1").shouldHave(text("File Upload Results"));
	    $("blockquote").shouldHave(text(fileUploadNote));
	    WebDriverRunner runner = new WebDriverRunner();

  }

	/**
	 * Test to execute SOAP service and parse response using rest assured library
	 */
  @Test
	public void testTogetResultFromSoapServiceResponse(){

	  String reuestPayload = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
			  "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
			  "  <soap12:Body>\n" +
			  "    <Add xmlns=\"http://tempuri.org/\">\n" +
			  "      <intA>100</intA>\n" +
			  "      <intB>200</intB>\n" +
			  "    </Add>\n" +
			  "  </soap12:Body>\n" +
			  "</soap12:Envelope>\n";
	  RequestSpecification xmlReqSpecBuilder= new RequestSpecBuilder()
			  .setBaseUri("http://www.dneonline.com/calculator.asmx")
			  //.setContentType(ContentType.XML)
			  .addHeader("SOAPAction","http://tempuri.org/Add")
			  . addHeader("Content-Type","text/xml; charset=utf-8")

			  .build();
	  ;


	  Response response=   RestAssured.given()
			  .spec(xmlReqSpecBuilder).log().body()
			  .body(reuestPayload).log().body()
			  .when().post()
			  .then().log().body()
			  .assertThat().statusCode(200)
			  .contentType(ContentType.XML).extract().response();

	  String result = new XmlPath(response.asString()).get("Envelope.Body.AddResponse.AddResult");
	  System.out.println(result);
	  fileUploadNote += result;


  }


}
