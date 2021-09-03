package com.mycompany.web.action;

import com.mycompany.automation.TestContext;
import com.mycompany.web.page.SiteFactory;

public class AppAction {

	SiteFactory siteFactory;
	public AppAction(SiteFactory siteFactory)
	{
		this.siteFactory = siteFactory;
	}
	
	/**
	   * Get an object of TestContext
	   * 
	   * @return TestContext
	   */

	  public TestContext getContext() {
	    return TestContext.get();
	  }
	
	  public NavigationAction _NavigationAction() {
		    return this.siteFactory.navigationAction();
	  }

	  public HomePageAction _HomePageAction() {
		    return this.siteFactory.homePageAction();
	  }

	  public SearchResultPageAction _SearchResultPageAction() {
		    return this.siteFactory.SearchResultPageAction();
	  }
	  
	  

}
