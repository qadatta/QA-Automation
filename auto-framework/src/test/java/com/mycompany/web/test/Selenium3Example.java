package com.mycompany.web.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mycompany.automation.BaseTest;
import com.mycompany.automation.TestAction;
import com.mycompany.web.locator.HomePageLocator;

public class Selenium3Example extends BaseTest{
	
	TestAction pageAction;
	
	@Test
    public void seleniumSampleTest() {
		
		
       
        pageAction = getContext().getTestAction();
        
        getContext().putTestObject("DM_TEST_DATA", "Datta");
        getContext().putTestObject("DM_TEST_DATA", "More");

        // And now use this to visit Google
        pageAction.getWebDriver().get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = pageAction.getWebElement(HomePageLocator.TF_SEARCH_FIELD1);

        // Enter something to search for
        pageAction.type(HomePageLocator.TF_SEARCH_FIELD1, "Cheese!");
        
        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + pageAction.getPageTitle());
        System.out.println(getContext().getTestObject("DM_TEST_DATA"));
      //  Reporter.log(pageAction.getPageTitle());
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(getContext().getWebDriver(), 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        
	}
	
	

}