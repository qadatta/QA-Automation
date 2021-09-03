package com.mycompany.web.page;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.mycompany.automation.Locator;
import com.mycompany.automation.LocatorType;
import com.mycompany.automation.PageAssert;
import com.mycompany.automation.TestAction;
import com.mycompany.automation.TestContext;

public class HomePage {

	public static final Locator SEARCH_FIELD = new Locator(
			LocatorType.CLASS_NAME, "LM6RPg", "Flip kat search box");
	public static final Locator SEARCH_BTN = new Locator(
			LocatorType.CSS_SELECTOR, "input[value='SEARCH']",
			"search submit button");
	public static final Locator LOGIN_LINK = new Locator(
			LocatorType.PARTIAL_LINK_TEXT, "Login", "Login link");
	public static final Locator LOGGED_IN_STATE = new Locator(
			LocatorType.CSS_SELECTOR, "li.greeting-link>a", "Logged in state");
	public static final Locator LOGOUT_LINK = new Locator(
			LocatorType.PARTIAL_LINK_TEXT, "Logout", "Logout link");
	public static final Locator SELECTED_FILTERS = new Locator(LocatorType.ID,
			"selectedFilters", "selectedFilters");

	public static String PAGE_TITLE = "Investing.com - Stock Market Quotes & Financial News";

	SiteFactory siteFactory;

	public TestAction testAction() {
		return TestContext.get().getTestAction();
	}

	public HomePage(SiteFactory siteFactory) {
		this.siteFactory = siteFactory;
	}

	public void searchKeyword(String keyword) {
		testAction().waitForElementVisible(SEARCH_FIELD);
		testAction().clearAndType(SEARCH_FIELD, keyword);
		testAction().click(SEARCH_BTN);

	}

	public void verifySearchResultPage(String keyword) {
		PageAssert.verifyTextPresentIn(SELECTED_FILTERS, keyword.toUpperCase());
		Reporter.log("Verified search term in upper case on search result page.");
	}

	public void retriveKeywordsAndThenSearchOnHomePage() {

		ArrayList<String> keywordList = retriveKeywordsToSearch();

		for (Iterator<String> iterator = keywordList.iterator(); iterator
				.hasNext();) {
			String keyword = (String) iterator.next();

			searchKeyword(keyword);

			verifySearchResultPage(keyword);

		}

	}

	public HomePage verifyUserLoggedInState() {
		testAction().waitForElementVisible(LOGGED_IN_STATE);
		PageAssert.verifyElementPresent(LOGGED_IN_STATE);
		return new HomePage(siteFactory);
	}

	public LoginModal clickOnLoginLink() {
		testAction().waitForElementVisible(LOGIN_LINK);
		Reporter.log("Click on Login Link present in Header");
		testAction().click(LOGIN_LINK);
		return new LoginModal(siteFactory);
	}

	public HomePage clickOnLogOutLink() {
		testAction().waitForElementVisible(LOGGED_IN_STATE);
		Reporter.log("Click on Logout Link present iin header");

		Actions action = new Actions(testAction().getWebDriver());
		action.moveToElement(testAction().getWebElement(LOGGED_IN_STATE))
				.build().perform();

		testAction().click(LOGOUT_LINK);
		return new HomePage(siteFactory);
	}

	public void verifyUserIsLogOutFromApplication() {
		testAction().waitForElementVisible(LOGIN_LINK);
		PageAssert.verifyElementPresent(LOGIN_LINK);
		Reporter.log("User is logged out from application");
	}

	public ArrayList<String> readColumnFromCSVFile(String fileName,
			String header) {

		ArrayList<String> keyWords = new ArrayList<String>();
		String splitBy = ",";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"src/test/java/com/comitydesigns/automation/testdata/"
							+ fileName));
			String line;
			int columnIndex = 0;
			line = br.readLine();
			boolean headerRecord = true;
			while (line != null) {
				String[] reocrd = line.split(splitBy);

				if (headerRecord) {
					for (int i = 0; i < reocrd.length; i++) {

						if (header.equals(reocrd[i]))
							columnIndex = i;
					}
					headerRecord = false;
				} else
					keyWords.add(reocrd[columnIndex]);
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return keyWords;
	}

	public ArrayList<String> retriveKeywordsToSearch() {
		ArrayList<String> keywordList = readColumnFromCSVFile("keywords.csv",
				"SearchKeywords");

		return keywordList;
	}

}
