package com.mycompany.automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestAction {

	private WebDriver driver;
	
	public TestAction(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public WebDriver getWebDriver()
	{
		return this.driver;
	}
	
	public WebElement getWebElement(Locator locator)
	{
		return driver.findElement(getWebElementType(locator));
	}
	
	public List<WebElement> getWebElements(Locator locator)
	{
		return driver.findElements(getWebElementType(locator));
	}
	
	/**
	 * Tread Wait for milliseconds
	 * @param miliSeconds time in milliseconds so that Test will wait for given time
	 */
	public void waitFor(long milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
	
	public void click(Locator locator)
	{
		getWebElement(locator).click();
	}

	public void type(Locator locator,String value)
	{
		getWebElement(locator).sendKeys(value);
	}
	
	public void clearAndType(Locator locator,String value)
	{
		getWebElement(locator).clear();
		getWebElement(locator).sendKeys(value);
	}
	
	public By getWebElementType(Locator locator)
	{
		LocatorType locatorType = locator.getLocatorType();
		By typeBy = null;
		
		switch (locatorType) {
		case ID:
			typeBy = By.id(locator.getLocatorValue());
			break;
		case NAME:
			typeBy = By.name(locator.getLocatorValue());
			break;
		case CLASS_NAME:
			typeBy = By.className(locator.getLocatorValue());
			break;
		case LINK_TEXT:
			typeBy = By.linkText(locator.getLocatorValue());
			break;	
		case PARTIAL_LINK_TEXT:
			typeBy = By.partialLinkText(locator.getLocatorValue());
			break;	
		case CSS_SELECTOR:
			typeBy = By.cssSelector(locator.getLocatorValue());
			break;	
		case TAG_NAME:
			typeBy = By.tagName(locator.getLocatorValue());
			break;	
		default:
			typeBy = By.xpath(locator.getLocatorValue());
			break;
		}
		
		return typeBy;
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public void quit()
	{
		driver.quit();
	}

	public void launchApplication() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		
	}

	public void clickOnLink(String linkText) {
		waitForElementVisible(new Locator(LocatorType.LINK_TEXT, linkText));
		click(new Locator(LocatorType.LINK_TEXT, linkText));
	}

	public boolean isElementDisplayed(Locator locator) {
		
		return getWebElement(locator).isDisplayed();
	}

	public void waitForElementVisible(Locator locator) {
		
		new WebDriverWait(getWebDriver(), 30).until(
		        ExpectedConditions.visibilityOf(getWebElement(locator)));
	}

	public void waitForPageLoad() {
	    ExpectedCondition<Boolean> pageLoadCondition = new
	        ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver driver) {
	                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	            }
	        };
	    WebDriverWait wait = new WebDriverWait(getWebDriver(), 60);
	    wait.until(pageLoadCondition);
	}

	public String getText(Locator locator) {

		return getWebElement(locator).getText();
	}
	

}
