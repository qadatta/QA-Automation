package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WYSIWYGEditorPage {
    private WebDriver driver;
    private String editorIframe = "mce_0_ifr";
    private By editArea = By.id("tinymce");
    private By increaseIndent = By.cssSelector("button[title='Increase indent']");



    public WYSIWYGEditorPage(WebDriver driver){
        this.driver = driver;
    }

    private void switchToEditor(){
        driver.switchTo().frame(editorIframe);
    }
    private void switchToMainWindow(){
        driver.switchTo().parentFrame();
    }

    public void clickIncreaseIndentButton(){
        driver.findElement(increaseIndent).click();
    }

    public void cleanEditor(){
        switchToEditor();
        driver.findElement(editArea).clear();
        switchToMainWindow();
    }
    public String getEditorText(){
        switchToEditor();
        String text = driver.findElement(editArea).getText();
        switchToMainWindow();
        return text;
    }

    public void enterTextInEditor(String text){
        switchToEditor();
        driver.findElement(editArea).sendKeys(text);
        switchToMainWindow();
    }

}
