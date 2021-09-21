package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private WebDriver driver;
    private By emailTextField = By.id("email");
    private By retrivePasswordBtn = By.id("form_submit");
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterRetrivePasswordEmail(String email){
        driver.findElement(emailTextField).sendKeys(email);
    }

    public EmailSentPage clickRetrivePasswordButton(){
        return new EmailSentPage(driver);
    }
}
