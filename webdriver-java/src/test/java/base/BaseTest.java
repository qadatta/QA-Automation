package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.util.List;

public class BaseTest {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp(){

        System.setProperty("webdriver.chrome.driver","resources/chromedriver");
        System.setProperty("webdriver.gecko.driver","resources/geckodriver");

        //        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-debugging-port=9222");
//        driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    openHomePage();
        ////        driver.manage().window().fullscreen();
////driver.manage().window().setSize(new Dimension(320,480));
//        System.out.println(driver.getTitle());
        homePage = new HomePage(driver);
   }

   @BeforeMethod
   public void openHomePage(){
       driver.get("https://the-internet.herokuapp.com");

   }

@AfterClass
    public void tearDown(){
    driver.quit();
}

public WebDriver getDriver(){
        return driver;
}


}
