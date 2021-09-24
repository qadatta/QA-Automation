package frame;

import base.BaseTest;
import org.testng.annotations.Test;

public class NestedFrameTests extends BaseTest {


    @Test
    public void nestedFrameExampleTest(){

        var nestedFrame = homePage.goToNestedFramePage();
        String LeftFrameText = nestedFrame.getLeftIframeText();
        System.out.println("LeftFrameText "+ LeftFrameText);

        String bottomFrameText = nestedFrame.getBottomframeText();
        System.out.println("bottomFrameText "+bottomFrameText);

    }

}
