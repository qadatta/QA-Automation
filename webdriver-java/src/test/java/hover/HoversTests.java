package hover;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HoversTests extends BaseTest {

    @Test
    public void userProfileHoverTest(){

        var hoversPage = homePage.clickOnHoursLink();
        var caption = hoversPage.hoverOnFigure(2);



        Assert.assertTrue(hoversPage.isFigureCaptionDisplayed(),"Figure caption is not displayed after mouse hover");
        String profileLink =   caption.getProfileLink();
        String profileLinkText =   caption.getProfileLinkText();
        String header =   caption.getTitle();
        Assert.assertEquals(header,"name: user1","Header is not correct");
        Assert.assertTrue(profileLink.endsWith("/users/1"),"Link is not correct");
        Assert.assertTrue(profileLinkText.equals("View profile"),"Link text is not correct");


    }

}
