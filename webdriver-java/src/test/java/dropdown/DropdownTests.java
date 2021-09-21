package dropdown;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownTests  extends BaseTest {

@Test
public void testToSelectDropDownOptions(){

    String optionToSelect = "Option 1";

    var dropDownPage = homePage.clickOnDropDown();

    dropDownPage.SelectDropDownOption(optionToSelect);
    var selectedOptions = dropDownPage.getSelectedOptions();
    Assert.assertEquals(selectedOptions.size(),1,"Section is not correct");
    Assert.assertTrue(selectedOptions.get(0).equals(optionToSelect),"Selection is not correct.");
}

}
