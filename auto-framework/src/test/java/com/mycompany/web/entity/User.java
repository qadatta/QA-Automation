package com.mycompany.web.entity;

import java.util.Hashtable;

import com.mycompany.automation.TestAction;
import com.mycompany.automation.TestContext;
import com.mycompany.web.action.NavigationAction;
import com.mycompany.web.page.SiteFactory;

public class User {

	public SiteFactory factory = new SiteFactory();
	
	public User()
	{
		
	}
	
	public User(Hashtable<String, String> userDatails)
	{
		
	}
	
	
	
	public TestAction testAction()
	{
		return TestContext.get().getTestAction();
	}

	public NavigationAction launchApplication()
	{
		testAction().launchApplication();
		return new NavigationAction(factory);
	}
	
}
