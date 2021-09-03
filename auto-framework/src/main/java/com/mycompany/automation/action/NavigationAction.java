package com.mycompany.automation.action;

import com.mycompany.automation.Page;
import com.mycompany.automation.SiteFactory;


public abstract class NavigationAction {

	SiteFactory siteFactory;

public abstract NavigationAction  navigateToHomePage();

public Page navigateTo(Page page)
{
	return new Page(siteFactory);

}
	
}
