package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class KeyPressesPage {
    private WebDriver driver;
    private By keyPressTextField = By.id("target");
    private By keyPressresult = By.id("result");

    public KeyPressesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String s){
        driver.findElement(keyPressTextField).sendKeys(s);
    }

    public void enterPiSymbol(){
        enterText(Keys.chord(Keys.ALT , "p"));
    }
    public String getResult(){
        return driver.findElement(keyPressresult).getText();
    }


}
