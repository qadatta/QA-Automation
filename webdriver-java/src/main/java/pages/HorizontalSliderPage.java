package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;


public class HorizontalSliderPage {
    private WebDriver driver;
    private By sliderContainer = By.cssSelector("div.sliderContainer");
    private By rangeValue = By.cssSelector("span#range");
    private By rangeSlider= By.tagName("input");


    public HorizontalSliderPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnHorizontalSlider(){
        driver.findElement(sliderContainer).click();
    }

    public double getSliderSelectedRange(){
        return Double.parseDouble(driver.findElement(rangeValue).getText());
    }

    public void clickRightArrowOnSlider(){
        System.out.println("Right arrow click");

        try {
            Thread.sleep(5000);
            WebElement rangeinput = driver.findElement( RelativeLocator.with(By.tagName("input")).toLeftOf(By.cssSelector("span#range")));
            System.out.println("a tag >>>>>> "+rangeinput.getText());

        }catch (Exception e){
System.out.println("---------"+e);
        }
        driver.findElement( RelativeLocator.with(rangeSlider).toLeftOf(rangeValue)).sendKeys(Keys.ARROW_RIGHT);
    }

    public void clickLeftArrowOnSlider(){
        driver.findElement(RelativeLocator.with(rangeSlider).toLeftOf(rangeValue)).sendKeys(Keys.ARROW_LEFT);
    }


}
