package com.mycompany.web.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.mycompany.automation.Configuration;
import com.mycompany.web.locator.HomePageLocator;

public class SeleniumWithoutFrameWorkTest {
	
	
	@Test
    public void seleniumSampleTest() {
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WIN10);
		WebDriver driver = null ;
       
try {
	System.out.println(Configuration.sProjectLocation + "\\src\\main\\resources\\drivers\\chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", Configuration.sProjectLocation + "\\src\\main\\resources\\drivers\\chromedriver.exe");

	driver = new RemoteWebDriver(new URL(Configuration.HUB_URL), capability);
			driver.manage().timeouts().implicitlyWait(Configuration.IMPLICITLY_WAIT, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			System.out.println("Error while initializing webdriver ");
			e.printStackTrace();
		}
		
        
        // And now use this to visit Google
		driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

      //  Reporter.log(pageAction.getPageTitle());
        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        
	}
	


}
