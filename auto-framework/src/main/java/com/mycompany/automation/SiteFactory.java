package com.mycompany.automation;


public class SiteFactory {

	
	public Page page()
	{
		return new Page(this);
	}
	
	public FrameworkAction _FrameworkAction()
	{
		return new FrameworkAction(this);
	}
	
	
	
}
