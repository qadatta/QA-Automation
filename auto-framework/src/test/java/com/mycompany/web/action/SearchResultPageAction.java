package com.mycompany.web.action;

import com.mycompany.web.page.SiteFactory;

public class SearchResultPageAction extends AppAction{

	SiteFactory siteFactory;
	
	public SearchResultPageAction(SiteFactory siteFactory) {
		super(siteFactory);
		this.siteFactory=siteFactory;
	}
	
	public SearchResultPageAction verifySearchResultPage(String keyword)
	{
		this.siteFactory.searchResultPage().verifySearchResultPage(keyword);
		return new SearchResultPageAction(siteFactory);
	}
	
	
	
}
