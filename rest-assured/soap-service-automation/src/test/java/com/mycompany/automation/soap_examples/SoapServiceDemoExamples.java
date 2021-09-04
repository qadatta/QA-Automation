package com.mycompany.automation.soap_examples;

import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;

public class SoapServiceDemoExamples {

    private static RequestSpecification requestSpecification;


    @BeforeClass
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://www.dneonline.com")
                .setContentType("text/xml; charset=utf-8")
                .addHeader("SOAPAction", "http://tempuri.org/Add")
                .build();
    }


    /**
     * To test status code
     */
    @Test
    public void testRunSoapServiceExample_VerifyStatusCode() {


        String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <Add xmlns=\"http://tempuri.org/\">\n" +
                "      <intA>100</intA>\n" +
                "      <intB>100</intB>\n" +
                "    </Add>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>\n";

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://www.dneonline.com")
                .setContentType("text/xml; charset=utf-8")
                .addHeader("SOAPAction", "http://tempuri.org/Add")
                .build();

        given().
                spec(requestSpecification)
                .body(input)
                .log().everything()
                .when()
                .post("/calculator.asmx")
                .then()
                .log().everything()
                .assertThat()
                .statusCode(200)
        ;
    }

    /**
     * Test to verify soap service response content
     */
    @Test
    public void testRunSoapServiceExample_VerifyResponseBody() {

        String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <Add xmlns=\"http://tempuri.org/\">\n" +
                "      <intA>100</intA>\n" +
                "      <intB>100</intB>\n" +
                "    </Add>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>\n";


        requestSpecification.baseUri("")
        given().
                spec(requestSpecification)
                .body(input)
                .log().everything()
                .when()
                .post("/calculator.asmx")
                .then().log().body()
                .assertThat()
                .body("Envelope.Body.AddResponse.AddResult", equalTo("200"));

    }

    /**
     * Test to verify soap service response content and use TestNG dataprovider to test service for multiple data
     */
    @Test(dataProvider = "AdditionDataProvider")
    public void testRunSoapServiceExample_VerifyResponseBody_using_dataProvider(int intA, int intB, int result) {

        String input = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <Add xmlns=\"http://tempuri.org/\">\n" +
                "      <intA>" + intA + "</intA>\n" +
                "      <intB>" + intB + "</intB>\n" +
                "    </Add>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>\n";

        given().
                spec(requestSpecification)
                .body(input)
                .log().everything()
                .when()
                .post("/calculator.asmx")
                .then().log().body()
                .assertThat()
                .body("Envelope.Body.AddResponse.AddResult", equalTo(String.valueOf(result)));

    }

    @DataProvider(name = "AdditionDataProvider")
    public Object[][] getData() {
        return new Object[][]{
                {10, 20, 30},
                {20, 30, 50},
                {110, 20, 140},
                {-10, -20, -30},
        };
    }

    @Test
    public void verifyXmlResponseDataOfList() {

    }

}


