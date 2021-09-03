package com.mycompany.web.locator;

import org.openqa.selenium.By;

import com.mycompany.automation.Locator;
import com.mycompany.automation.LocatorType;

public class HomePageLocator {

	public static final By TF_SEARCH_FIELD = By.name("q");
	
	public static final Locator TF_SEARCH_FIELD1 = new Locator(LocatorType.NAME, "q"); 
	
}
