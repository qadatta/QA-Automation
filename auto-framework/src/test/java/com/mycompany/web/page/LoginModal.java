package com.mycompany.web.page;

import org.testng.Reporter;

import com.mycompany.automation.Locator;
import com.mycompany.automation.LocatorType;
import com.mycompany.automation.TestAction;
import com.mycompany.automation.TestContext;

public class LoginModal{
	
	public static final Locator LOGIN_EMAIL_TF = new Locator(LocatorType.CSS_SELECTOR, "input.user-email","Email text field"); 
	public static final Locator LOGIN_PWD_TF = new Locator(LocatorType.CSS_SELECTOR, "input.user-pwd","password text field"); 
	public static final Locator LOG_IN_LINK = new Locator(LocatorType.CSS_SELECTOR, "input.login-btn","login button on modal"); 


	
	SiteFactory siteFactory;


	public TestAction testAction()
	{
		return TestContext.get().getTestAction();
	}

	public LoginModal(SiteFactory siteFactory) {
		this.siteFactory = siteFactory;
	}

	public LoginModal inputUserEmailAndPasswordForLogin(String email,String pwd)
	{
		enterLoginEmail(email);
		enterLoginPassword(pwd);
		return new LoginModal(siteFactory);
	}
	
	public void enterLoginEmail(String email)
	{
		
		Reporter.log("Enter email - "+email +" for login to application");
		testAction().waitForElementVisible(LOGIN_EMAIL_TF);
		testAction().clearAndType(LOGIN_EMAIL_TF, email);

	}
	
	public void enterLoginPassword(String pwd)
	{
		Reporter.log("Enter password - "+pwd +" for login to application");

		testAction().waitForElementVisible(LOGIN_PWD_TF);
		
		testAction().clearAndType(LOGIN_PWD_TF, pwd);
	}
	
	public void clickOnLoginInButton()
	{
		Reporter.log("Click on Log in button on modal");

		testAction().waitForElementVisible(LOG_IN_LINK);
		testAction().click(LOG_IN_LINK);
	}
	
	public HomePage loginToApplication(String email,String pwd)
	{
		enterLoginEmail(email);
		enterLoginPassword(pwd);
		clickOnLoginInButton();
		return new HomePage(siteFactory);
	}
	
	
}
