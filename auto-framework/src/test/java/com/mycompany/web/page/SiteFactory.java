package com.mycompany.web.page;

import com.mycompany.web.action.HomePageAction;
import com.mycompany.web.action.NavigationAction;
import com.mycompany.web.action.SearchResultPageAction;

public class SiteFactory {


	//Actions
	public NavigationAction navigationAction() {
		return new NavigationAction(this);
	}
	public HomePageAction homePageAction() {
		return new HomePageAction(this);
	}
	
	public SearchResultPageAction SearchResultPageAction() {
		return new SearchResultPageAction(this);
	}

	
	//Application pages
	
	public HomePage homePage()
	{
		return new HomePage(this);
	}
	public LoginModal loginModal()
	{
		return new LoginModal(this);
	}
	public SearchResultPage searchResultPage()
	{
		return new SearchResultPage(this);
	}

	

		
	
	
		
}
