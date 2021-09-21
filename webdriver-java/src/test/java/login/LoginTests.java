package login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SecureAreaPage;

public class LoginTests extends BaseTest {



@Test
public void loginSuccessTest(){

    LoginPage loginPage = homePage.clickFormAuthenticationLink();

    loginPage.setUserName("tomsmith");
    loginPage.setUserPassword("SuperSecretPassword!");
    SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
    Assert.assertTrue(secureAreaPage.getFlashMessage().contains("You logged into a secure area!"),"Alert message is incorrect");
}

}
