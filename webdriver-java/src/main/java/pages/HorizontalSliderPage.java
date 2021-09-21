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

        driver.findElement( RelativeLocator.with(rangeSlider).toLeftOf(rangeValue)).sendKeys(Keys.ARROW_RIGHT);
    }

    public void clickLeftArrowOnSlider(){
        driver.findElement(RelativeLocator.with(rangeSlider).toLeftOf(rangeValue)).sendKeys(Keys.ARROW_LEFT);
    }


    public void setHorizontalSliderRange(int value) {
        double rangeValue = getSliderSelectedRange();
        do {
            if(rangeValue<value)
                clickRightArrowOnSlider();
            else
                clickLeftArrowOnSlider();
            rangeValue = getSliderSelectedRange();
            System.out.println(rangeValue);
        }while(rangeValue !=value);
    }
}
