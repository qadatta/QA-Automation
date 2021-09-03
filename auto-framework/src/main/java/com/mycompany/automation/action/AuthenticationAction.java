package com.mycompany.automation.action;

import com.mycompany.automation.SiteFactory;



public abstract class AuthenticationAction {

	SiteFactory siteFactory;
	
	public AuthenticationAction(SiteFactory siteFactory) {
		this.siteFactory = siteFactory;
	}

	
	public abstract AuthenticationAction verifyApplicationLoggedInState();

//public AuthenticationAction registerUser(Hashtable<String,String> userDetails);
	
}
