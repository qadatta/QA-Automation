package com.mycompany.automation;



public class Page {

	SiteFactory siteFactory;
	
	public TestAction testAction()
	{
		return TestContext.get().getTestAction();
	}

	public Page(SiteFactory siteFactory) {
		this.siteFactory = siteFactory;
	}

	public void verifyPageTitleContains(String pageTitle)
	{
		PageAssert.verifyPageTitleContains(pageTitle);
	}
	
	public FrameworkAction launchApplication() {
		testAction().getWebDriver().get("https://www.google.co.in/");
		return new FrameworkAction(siteFactory);
	}
}
