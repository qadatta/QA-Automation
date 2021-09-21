package keypress;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KeyPressTests extends BaseTest {

    @Test
    public void backSpaceTest(){
        var keyPressesPage = homePage.clickKeyPressesLink();
        keyPressesPage.enterText("D"+ Keys.BACK_SPACE);
        Assert.assertTrue(keyPressesPage.getResult().contains("BACK_SPACE"),"Entered ke is not BACK_SPACE");
    }

    @Test
    public void enterPiTest(){
        var keyPressPage = homePage.clickKeyPressesLink();
         keyPressPage.enterPiSymbol();
        Assert.assertEquals(keyPressPage.getResult(),"aa", "Pi Symbol is not correct");
    }
}
