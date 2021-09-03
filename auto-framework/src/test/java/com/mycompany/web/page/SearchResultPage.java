package com.mycompany.web.page;

import com.mycompany.automation.Locator;
import com.mycompany.automation.LocatorType;
import com.mycompany.automation.PageAssert;
import com.mycompany.automation.TestAction;
import com.mycompany.automation.TestContext;


public class SearchResultPage {

	
	public static final Locator SELECTED_FILTERS = new Locator(LocatorType.ID, "selectedFilters","selectedFilters"); 

	SiteFactory siteFactory;


	public TestAction testAction()
	{
		return TestContext.get().getTestAction();
	}

	public SearchResultPage(SiteFactory siteFactory) {
		this.siteFactory = siteFactory;
	}


	
	public void verifySearchResultPage(String keyword)
	{
		PageAssert.verifyTextPresentIn(SELECTED_FILTERS,keyword);
	}


	
}
