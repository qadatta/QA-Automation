package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramePage {
    private WebDriver driver;

    private String topFrame = "frame-top";
    private String leftIframeName = "frame-left";
    private By bottomIframe = By.name("frame-bottom");

    public NestedFramePage(WebDriver driver){
        this.driver=driver;
    }

    private void switchToITopFrame(){
        driver.switchTo().frame(topFrame);
    }
    private void switchToMainWindow(){
        driver.switchTo().defaultContent();
    }
    private void switchToLeftFrame(){
        driver.switchTo().frame(leftIframeName);
    }
    private void switchToBottomFrame(){
        driver.switchTo().frame(driver.findElement(bottomIframe));
    }

    public String getLeftIframeText(){

        switchToITopFrame();
        switchToLeftFrame();
        String text = driver.findElement(By.tagName("body")).getText();
        switchToMainWindow();
        return text;
    }

    public String getBottomframeText(){
        switchToBottomFrame();
        String text = driver.findElement(By.tagName("body")).getText();
        switchToMainWindow();
        return text;
    }



}
