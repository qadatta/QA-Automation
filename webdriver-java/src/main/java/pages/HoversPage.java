package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HoversPage {
    private WebDriver driver;
    private By figureBox = By.className("figure");
    private By boxCaption;

    public HoversPage(WebDriver driver){
        this.driver = driver;
    }

    public FigureCaption hoverOnFigure(int index){

        By figureCaption = By.xpath("//div["+index+"]/div[@class='figcaption']");
        boxCaption = figureCaption;

        new Actions(driver)
                .moveToElement(driver.findElements(figureBox).get(index-1))
                .perform();

        return new FigureCaption(driver.findElement(figureCaption));
    }

    public boolean isFigureCaptionDisplayed(){
        return driver.findElement(boxCaption).isDisplayed();
    }

    public class FigureCaption{
        private WebElement caption ;
        private By header = By.tagName("h5");
        private By link = By.tagName("a");

        public FigureCaption(WebElement caption){
            this.caption = caption;
        }
        public String getTitle(){
            return caption.findElement(header).getText();
        }
        public String getProfileLink(){
            return caption.findElement(link).getAttribute("href");
        }
        public String getProfileLinkText(){
            return caption.findElement(link).getText();
        }
        public boolean isFigureCaptionDisplayed(){
            return caption.isDisplayed();
        }
    }

}
