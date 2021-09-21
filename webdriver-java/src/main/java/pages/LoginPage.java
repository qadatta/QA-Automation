package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private By userNameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("form#login button");

    public void setUserName(String userName) {
        driver.findElement(userNameField).sendKeys(userName);
    }

    public void setUserPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public SecureAreaPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new SecureAreaPage(driver);
    }
}
