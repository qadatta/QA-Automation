package com.mycompany.web.action;

import com.mycompany.web.page.SearchResultPage;
import com.mycompany.web.page.SiteFactory;

public class HomePageAction extends AppAction{

	SiteFactory siteFactory;
	
	public HomePageAction(SiteFactory siteFactory) {
		super(siteFactory);
		this.siteFactory=siteFactory;
	}
	
	public SearchResultPage searchKeyword(String keyword)
	{
		this.siteFactory.homePage().searchKeyword(keyword);
		return new SearchResultPage(siteFactory);
	}

	
	public SearchResultPage searchResultAndVerifyResultPage(String keyword)
	{
		searchKeyword(keyword);
		siteFactory.searchResultPage().verifySearchResultPage(keyword);
		
		return new SearchResultPage(siteFactory);
	}
	
	
}
