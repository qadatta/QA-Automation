package com.mycompany.automation;

import org.testng.Assert;

public class PageAssert {

	public static TestAction getTestAction()
	{
		return TestContext.get().getTestAction();
	}
	
	public static void verifyPageTitle(String pageTitle)
	{
		getTestAction().waitForPageLoad();
		Assert.assertEquals(getTestAction().getPageTitle(), pageTitle, "Page Title is not as expected: "+getTestAction().getPageTitle());
	}

	public static void verifyPageTitleContains(String pageTitle)
	{
		getTestAction().waitForPageLoad();
		if(false==getTestAction().getPageTitle().contains(pageTitle))
			Assert.assertTrue(false, "Actual page title: \"" +getTestAction().getPageTitle() + " \" not contains \"" + pageTitle + "\"");
	}
	public static void verifyElementPresent(Locator locator) {
		if(false==getTestAction().isElementDisplayed(locator))
			getTestAction().waitForElementVisible(locator);
		Assert.assertTrue(getTestAction().isElementDisplayed(locator),"Element '"+locator.getLocatorDoc()+"' not present on page");
		
	}

	public static void verifyTextPresentIn(Locator locator,String textToVerify) {
		getTestAction().waitForElementVisible(locator);
		if(false==getTestAction().getText(locator).contains(textToVerify))
			Assert.fail("'"+textToVerify+"' is not present in Element '"+locator.getLocatorDoc()+"'");
	}
	
}
