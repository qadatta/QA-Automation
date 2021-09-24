package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage{

    private WebDriver driver;
    private By ClickForJSAlertButton = By.xpath(".//button[text()='Click for JS Alert']");
    private By ClickForJSConfirmButton = By.xpath(".//button[text()='Click for JS Confirm']");
    private By ClickForJSPromptButton = By.xpath(".//button[text()='Click for JS Prompt']");
    private By alertResult = By.cssSelector("p#result");


    public JavaScriptAlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void triggerJSAlert(){
        driver.findElement(ClickForJSAlertButton).click();
    }

    public void triggerConfirmAlert(){
        driver.findElement(ClickForJSConfirmButton).click();
    }

    public void triggerPromptAlert(){
        driver.findElement(ClickForJSPromptButton).click();
    }

    public String getAlertResult(){
      return   driver.findElement(alertResult).getText();
    }

    public void alert_clickToAccept(){
        driver.switchTo().alert().accept();
    }

    public void alert_clickToDismiss(){
        driver.switchTo().alert().dismiss();
    }

    public void alert_setInput(String inputText){
        driver.switchTo().alert().sendKeys(inputText);
    }
    public String alert_getText(){
        return driver.switchTo().alert().getText();
    }

}
