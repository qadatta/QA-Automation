package formsubmit;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class FormSubmitTest extends BaseTest {

private WebDriver driver;

@Test
public void submitFormTest() throws InterruptedException {
    driver =  getDriver();

    driver.get("https://formy-project.herokuapp.com/form");

    driver.findElement(By.id("first-name")).sendKeys("Datta");

    driver.findElement(By.id("last-name")).sendKeys("More");

    driver.findElement(By.id("job-title")).sendKeys("Technologist");

    driver.findElement(By.id("radio-button-2")).click();
    driver.findElement(By.xpath("//div/input[@value='checkbox-1']")).click();

    Select select = new Select(driver.findElement(By.id("select-menu")));
    select.selectByVisibleText("10+");

    driver.findElement(By.id("datepicker")).sendKeys("09/11/2021");


    driver.findElement(By.linkText("Submit")).click();

    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofSeconds(5))
            .ignoring(NoSuchElementException.class);

    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
        public WebElement apply(WebDriver driver) {
            return driver.findElement(By.cssSelector("div.alert-success"));
        }
    });

    System.out.println(driver.findElement(By.cssSelector("div.alert-success")).getText());
    System.out.println(driver.getTitle());
}

}
