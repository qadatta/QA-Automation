package com.mycompany.web.entity;

import java.util.Hashtable;

import com.mycompany.web.page.SiteFactory;

public class As {

		public SiteFactory factory = new SiteFactory();
		static Hashtable<String, String>  userDetails = new Hashtable<String, String>();

		public static final User guestUser = new User();
		public static final User registeredUser = new User(userDetails);

	/**
	 * Entity for User [Guest User]
	 * 
	 * @param methodName
	 */
	public  void guestUser() {
		//siteFactory.navigationAction().
	}
	
}
