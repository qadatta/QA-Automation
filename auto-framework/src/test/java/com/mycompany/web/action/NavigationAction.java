package com.mycompany.web.action;

import com.mycompany.web.page.SiteFactory;

public class NavigationAction extends AppAction{

	SiteFactory siteFactory;
	
	public NavigationAction(SiteFactory siteFactory) {
		super(siteFactory);
		this.siteFactory=siteFactory;
	}
	

	
	
}
