package com.mycompany.web.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mycompany.automation.BaseTest;
import com.mycompany.web.entity.As;
import com.mycompany.web.page.SiteFactory;

public class FlipKartTest extends BaseTest {

	SiteFactory factory;

	

	@Test(groups = { "SmokeTest" })

	public void keywordSearchTest() {

		String keyword = "refrigerator";
		As.guestUser.launchApplication()._NavigationAction()._HomePageAction()
				.searchKeyword(keyword)
				.verifySearchResultPage(keyword.toUpperCase());
		
	}


	@DataProvider(name = "fk-test-data")
	public static Object[][] searchKeywords() {
		return new Object[][] { { "MOTO G", "641,849" },
				{ "MOTO E", "254,125" } };
	}

	@Test(dataProvider = "fk-test-data", groups = { "RegressionTest" })
	public void flipKartTestSearchTest(String keyword, String expectedResult) {

		System.out.println(keyword + " <----------------> " + expectedResult);
		As.guestUser.launchApplication()
		._HomePageAction()
		.searchResultAndVerifyResultPage(keyword);

	}

}
