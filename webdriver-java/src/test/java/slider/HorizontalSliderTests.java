package slider;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HorizontalSliderTests extends BaseTest {

@Test
public void setHorizpntalSliderTestForRangeValue4(){

    var slider = homePage.clickHorizontalSliderLink();

    slider.clickRightArrowOnSlider();

    slider.setHorizontalSliderRange(4);

    double sliderSelectedRange = slider.getSliderSelectedRange();
    Assert.assertTrue(4 == sliderSelectedRange ,"Slider selected range is not 4, it is " + sliderSelectedRange);

}

}
