package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropDownPage {

    private By dropDown = By.cssSelector("select#dropdown");
    private WebDriver driver;

    public DropDownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void SelectDropDownOption(String option){
        Select select = new Select(driver.findElement(dropDown));

        select.selectByVisibleText(option);
    }

    public List<String> getSelectedOptions(){
        Select select = new Select(driver.findElement(dropDown));

        List<String> selectedOptions =  select.getAllSelectedOptions().stream().map(e -> e.getText()).collect(Collectors.toList());
        return selectedOptions;
    }

}
