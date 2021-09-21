package slider;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HorizontalSliderTests extends BaseTest {

@Test
public void setHorizpntalSliderTestForRangeValue4(){

    var slider = homePage.clickHorizontalSliderLink();

    slider.clickRightArrowOnSlider();

    double rangeValue = slider.getSliderSelectedRange();

    do {
        if(rangeValue<4)
            slider.clickRightArrowOnSlider();
        else
            slider.clickLeftArrowOnSlider();
        rangeValue = slider.getSliderSelectedRange();
        System.out.println(rangeValue);
    }while(rangeValue !=4);
    double sliderSelectedRange = slider.getSliderSelectedRange();
    Assert.assertTrue(4 == sliderSelectedRange ,"Slider selected range is not 4, it is " + sliderSelectedRange);

}

}
