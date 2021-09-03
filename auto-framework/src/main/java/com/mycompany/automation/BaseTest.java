package com.mycompany.automation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class BaseTest {

	
	public TestContext getContext() {

		return TestContext.get();
	}
	
	public TestAction getPageAction() {
		return getContext().getTestAction();
	}
	

	
	@BeforeMethod( alwaysRun = true )
	public void setUp() throws InterruptedException {
		System.out.println("Set Up >> "+getContext().getWebDriver().hashCode());

		getContext().put("internalError", false);
		//Reporter.log("Test Started: " + method.getName());

	}	
	
	@AfterMethod( alwaysRun = true )
	public void tearDown() {
		//do nothing
		//getContext().getWebDriver().quit();
		System.out.println("Tear Down >> "+getContext().getWebDriver().hashCode());
		
	}

	public void log( String message ) {
		//Reporter.log( Reporter.getCurrentTestResult().getMethod() + message );
	}
	

}
