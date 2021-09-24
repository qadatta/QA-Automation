package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private void clickOnLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public LoginPage clickFormAuthenticationLink(){
        clickOnLink("Form Authentication");
        return new LoginPage(driver);
    }
    public DropDownPage clickOnDropDown() {
        clickOnLink("Dropdown");
        return new DropDownPage(driver);
    }

    public ForgotPasswordPage clickForgotPasswordLink(){
        clickOnLink("Forgot Password");
        return new ForgotPasswordPage(driver);
    }

    public HoversPage clickOnHoursLink(){
        clickOnLink("Hovers");
        return new HoversPage(driver);
    }

    public KeyPressesPage clickKeyPressesLink(){
        clickOnLink("Key Presses");
        return new KeyPressesPage(driver);
    }

    public HorizontalSliderPage clickHorizontalSliderLink(){
        clickOnLink("Horizontal Slider");
        return new HorizontalSliderPage(driver);
    }

    public JavaScriptAlertsPage clickJavaScriptAlertsLink(){
        clickOnLink("JavaScript Alerts");
        return new JavaScriptAlertsPage(driver);
    }

    public WYSIWYGEditorPage clickWYSIWYGEditorLink(){
        clickOnLink("WYSIWYG Editor");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new WYSIWYGEditorPage(driver);
    }

    public NestedFramePage goToNestedFramePage(){
        driver.findElement(By.linkText("Nested Frames")).click();
        return new NestedFramePage(driver);
    }
}
