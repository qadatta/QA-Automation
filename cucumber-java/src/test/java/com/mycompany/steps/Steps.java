package com.mycompany.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mycompany.base.BaseUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Steps extends BaseUtil {
	
	private BaseUtil baseUtil;
	
	public Steps(BaseUtil util) {
		this.baseUtil = util;
	}
	
	private WebDriver driver;
	
	@Before()
	public void setup() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
//		System.setProperty("webdriver.gecko.driver","resources/geckodriver");
			driver = new ChromeDriver();
//		driver = new HtmlUnitDriv();
	}
	
	
	
	/* Code for Chapter 3.1
	 
	 @Given("I am in the login page of the Para Bank Application")
	 public void i_am_in_the_login_page_of_the_Para_Bank_Application() {
		System.setProperty(“webdriver.chrome.driver”, “C:\\Drivers\\chromedriver.exe”);
		driver = new ChromeDriver();
		driver.get(http://parabank.parasoft.com/parabank/index.htm);
	}
	
	@When("I enter valid credentials")
	public void i_enter_valid_credentials() {
		
		driver.findElement(By.name(“username”)).sendKeys(“tautester”);
		driver.findElement(By.name(“password”)).sendKeys(“password”);
		driver.findElement(By.name(“username”)).submit();
	}
	
	@Then("I should be taken to the Overview page")
	public void i_should_be_taken_to_the_Overview_page() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(“//*[@id=\rightPanel\”]/div/div/h1”)));
	
		driver.findElement(By.xpath(“//*[@id=\rightPanel\”]/div/div/h1”)).isDisplayed();
		driver.findElement(By.linkText(“Log out”)).click()’
		driver.quit();	
	}
	
	*/
	
	
	@Given("I am in the login page")
	@Given("I am in the login page of the Para Bank Application")
	public void i_am_in_the_login_page_of_the_Para_Bank_Application() {
	
		
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
	}
	


	/* Example Step for Scenario Outline
	 
	   	@When("I enter valid credentials")
		public void i_enter_valid_credentials(String username, String password, String userFullName) {
		
			baseUtil.userFullName = userFullName;
		
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.name("username")).submit();
	  
		}*/
	
	@When("I enter valid {string} and {string}")
	public void i_enter_valid_credentials_(String username, String password) {
		
		baseUtil.userFullName = userFullName;
		
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("username")).submit();
	  
	}


	@When("I enter valid {string} and {string} with {string}")
	public void i_enter_valid_credentials(String username, String password, String userFullName) {
		
		baseUtil.userFullName = userFullName;
		
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("username")).submit();
	  
	}

	@Then("I should be taken to the Overview page")
	public void i_should_be_taken_to_the_Overview_page() throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("smallText")));
		
		String actualuserFullName = driver.findElement(By.className("smallText")).getText();

		System.out.println(baseUtil.getUserFullName());

		assertTrue(actualuserFullName, actualuserFullName.contains(baseUtil.getUserFullName()));
		
		driver.findElement(By.linkText("Log Out")).click();
		
		// driver.quit();
	  
	}
	

	@When("I enter valid credentials")
	public void i_enter_valid_credentials(DataTable table) {
		
		List<String> loginForm=table.asList();

		driver.findElement(By.name("username")).sendKeys(loginForm.get(0));
		driver.findElement(By.name("password")).sendKeys(loginForm.get(1));
		driver.findElement(By.name("username")).submit();
	}


	@After()
	public void quitBrowser(Scenario scenario) {

		if (scenario.isFailed()) {
			// Take a screenshot...
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
		}
		driver.quit();
		
	}

	@When("User details api executed")
	public void userDetailsApiExecuted() {
		String reuestPayload = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
				"<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
				"  <soap12:Body>\n" +
				"    <ListOfContinentsByName xmlns=\"http://www.oorsprong.org/websamples.countryinfo\"/>\n" +
				"  </soap12:Body>\n" +
				"</soap12:Envelope>\n";
		RequestSpecification xmlReqSpecBuilder= new RequestSpecBuilder()
				.setBaseUri("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso")
				.setContentType(ContentType.XML)
				//.addHeader("SOAPAction","http://tempuri.org/Add").
				.      addHeader("Content-Type","text/xml; charset=utf-8")

				.build();
		//.config(RestAssured.config().xmlConfig(XmlConfig.xmlConfig().declareNamespace("test", "http://www.oorsprong.org/websamples.countryinfo")))
		;


		Response response=   RestAssured.given()
				.spec(xmlReqSpecBuilder).log().body()
				.body(reuestPayload).log().body()
				.when().post()
				.then().log().body()
				.assertThat().statusCode(200)
				.contentType(ContentType.XML).extract().response();

		List<String> sName = new XmlPath(response.asString()).getList("Envelope.Body.ListOfContinentsByNameResponse.ListOfContinentsByNameResult.tContinent.sName");
		System.out.println(sName);
		baseUtil.setUserFullName(userFullName = sName.get(2));
	}

	@When("User details api executed - new")
	public void userDetailsApiExecutedNew() {
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
		baseUtil.setUserFullName(userFullName = result);
	}

	@Given("I am on google home page")
	public void iAmOnGoogleHomePage() {
		driver.get("https://www.google.com/");
	}

	@When("google search using api response value")
	public void googleSearchUsingApiResponseValue() {
		driver.findElement(By.name("q")).sendKeys(baseUtil.getUserFullName() + Keys.ENTER);
	}

	@Then("verify api response in result page")
	public void verifyApiResponseInResultPage() {
		String searchXpath = "//h3[contains(.,'%s - Wiki')]";

		By searchLink = By.xpath(String.format(searchXpath,baseUtil.getUserFullName()));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(searchLink));

		List<WebElement> links = driver.findElements(searchLink);

		System.out.println("---------- Elements found are ---------------");

		for (WebElement e: links) {
			System.out.println(e.getText());
		}


	}
}
