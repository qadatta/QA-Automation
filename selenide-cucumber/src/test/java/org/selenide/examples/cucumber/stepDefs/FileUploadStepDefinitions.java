package org.selenide.examples.cucumber.stepDefs;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.io.File;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.OutputType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.codeborne.selenide.AssertionMode;
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
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FileUploadStepDefinitions {
	private String keyword;
	private String fileUploadNote = "";

	@After()
	public void quitBrowser(Scenario scenario) {

		if (scenario.isFailed()) {
			System.out.println("***************** FAILED *********************");
			if (scenario.isFailed()) {
				// Take a screenshot...
				String screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64);
				byte[] screenshot = Base64.getDecoder().decode(screenshotAsBase64);
				scenario.attach(screenshot, "image/png", "Failure Screenshot");

			}
		} else {
			System.out.println("========= Scenario passed ================");
			// Take a screenshot...
			String screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64);
			byte[] screenshot = Base64.getDecoder().decode(screenshotAsBase64);
			scenario.attach(screenshot, "image/png", "Success Screenshot");

		}

	}

	@Given("user executed soap service and set required veriables for scenario")
	public void user_executed_soap_service_and_set_required_veriables_for_scenario() {
		String reuestPayload = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
				+ "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" + "  <soap12:Body>\n"
				+ "    <Add xmlns=\"http://tempuri.org/\">\n" + "      <intA>100</intA>\n" + "      <intB>200</intB>\n"
				+ "    </Add>\n" + "  </soap12:Body>\n" + "</soap12:Envelope>\n";
		RequestSpecification xmlReqSpecBuilder = new RequestSpecBuilder()
				.setBaseUri("http://www.dneonline.com/calculator.asmx")
				// .setContentType(ContentType.XML)
				.addHeader("SOAPAction", "http://tempuri.org/Add").addHeader("Content-Type", "text/xml; charset=utf-8")

				.build();
		;

		Response response = RestAssured.given().spec(xmlReqSpecBuilder).log().body().body(reuestPayload).log().body()
				.when().post().then().log().body().assertThat().statusCode(200).contentType(ContentType.XML).extract()
				.response();

		String result = new XmlPath(response.asString()).get("Envelope.Body.AddResponse.AddResult");
		System.out.println(result);
		fileUploadNote += result;
	}

	@Given("user executed soap service request payload using file and set required variable for scenario")
	public void userExecutedSoapServiceRequestPayloadUsingFileAndSetRequiredVariableForScenario() {

	    File resourcesDirectory = new File("src/test/resources");

		File xmlFile = new File(resourcesDirectory.getAbsolutePath()+ "/payload_templates/add-request-payload.xml");
		RequestSpecification xmlReqSpecBuilder = new RequestSpecBuilder()
				.setBaseUri("http://www.dneonline.com/calculator.asmx").setContentType(ContentType.XML)
				.addHeader("SOAPAction", "http://tempuri.org/Add").addHeader("Content-Type", "text/xml; charset=utf-8")
				.build();
		

		Response response = RestAssured.given().spec(xmlReqSpecBuilder).log().body().body(xmlFile).log().body().when()
				.post().then().log().body().assertThat().statusCode(200).contentType(ContentType.XML).extract()
				.response();

		String result = new XmlPath(response.asString()).get("Envelope.Body.AddResponse.AddResult");
		System.out.println(result);
		fileUploadNote += result;

	}

	@Given("user is on file upload page")
	public void user_is_on_file_upload_page() {
		Configuration.reportsFolder = "target/surefire-reports";
		Configuration.assertionMode = AssertionMode.SOFT;
		open("https://cgi-lib.berkeley.edu/ex/fup.html");
	}

	@When("user select file {string} to upload")
	public void user_select_file_hello_world_txt_to_upload(String fileName) {
		
	    File resourcesDirectory = new File("src/test/resources");
		File file = $("input[name='upfile']").uploadFile(new File(resourcesDirectory.getAbsolutePath()+"/upload_files/"+ fileName));

	}

	@When("enter {string} in input field")
	public void enter_in_input_field(String notes) {
		$("input[name='note'").setValue(notes);
		System.out.println("fileUploadNote >" + notes);

	}

	@When("click \"Press\"button to upload file")
	public void click_press_button_to_upload_file() {
		$("input[value='Press'").click();
	}

	@Then("{string} visible on file upload result")
	public void visible_on_file_upload_result(String notes) {
		$("h1").shouldHave(text("File Upload Results"));
		$("blockquote").shouldHave(text(notes));
		System.out.println("fileUploadNote>>" + notes);

	}

	@And("enter note parsed from api response into input field")
	public void enterNoteParsedFromApiResponseIntoInputField() {
		$("input[name='note'").setValue(fileUploadNote);
		System.out.println("fileUploadNote >" + fileUploadNote);

	}

	@Then("note from soap service response is visible on file upload result")
	public void noteFromSoapServiceResponseIsVisibleOnFileUploadResult() {
		$("h1").shouldHave(text("File Upload Results"));
		$("blockquote").shouldHave(text(fileUploadNote));
		System.out.println("fileUploadNote>>" + fileUploadNote);

	}

//
//  @When("click \"Images\" link")
//  public void chooseImagesAsSearchTarget() {
//    $(byText("Images")).click();
//  }
//
//  @When("enter a keyword {string} in input field")
//  public void enterKeyword(String keyword) {
//    this.keyword = keyword;
//    $(By.name("q")).val(keyword).pressEnter();
//  }
//
//  @Then("at least top {int} matching images should be shown")
//  public void topTenMatchedImagesShouldBeShown(int resultsCount) {
//    $$(".rg_i").shouldHave(sizeGreaterThanOrEqual(resultsCount));
//  }

	@Given("user has soap service request for file upload")
	public void user_has_soap_service_request_for_file_upload() {
		System.out.println("User will prepare soap request here...");
	}

	@When("user prepare request by updating following values in template")
	public void user_prepare_request_by_updating_following_values_in_template(
			io.cucumber.datatable.DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b;
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		try {
			b = f.newDocumentBuilder();
		    File resourcesDirectory = new File("src/test/resources");

			Document doc = b.parse(new File(resourcesDirectory.getAbsolutePath()+"/payload_templates/request_payload.xml"));
			
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			for (Map<String, String> columns : rows) {
				// store.addBook(new Book(columns.get("title"), columns.get("author")));
				System.out.println("xmlpath: " + columns.get("xmlpath") + " nodeValue: " + columns.get("nodeValue"));
				
				Node node = (Node) xPath.compile(columns.get("xmlpath")).evaluate(doc, XPathConstants.NODE);
				node.setTextContent(columns.get("nodeValue"));
			}

			
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource domSource = new DOMSource(doc);
			StreamResult sr = new StreamResult(new File(resourcesDirectory.getAbsolutePath()+"/payload_templates/request_payload.xml"));
			tf.transform(domSource, sr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("submitted request with updated payload")
	public void submitted_request_with_updated_payload() {
		// Write code here that turns the phrase above into concrete actions
	}

	@Then("response has updated values")
	public void response_has_updated_values() {

		System.out.println("Verification will be added here...");
	}

}
