package alerts;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertTests extends BaseTest {

    @Test
    public void testAlertExample(){


       var javaScriptAlertPage=  homePage.clickJavaScriptAlertsLink();

       javaScriptAlertPage.triggerJSAlert();
       javaScriptAlertPage.alert_clickToAccept();

        Assert.assertTrue(javaScriptAlertPage.getAlertResult().equals("You successfully clicked an alert"),"Alert result message is not correct");

    }

    @Test
    public void testAlertConfirmDismiss(){
        var javaScriptAlertPage = homePage.clickJavaScriptAlertsLink();

        javaScriptAlertPage.triggerConfirmAlert();
        String alertMessage = javaScriptAlertPage.alert_getText();
        Assert.assertTrue(alertMessage.contains("I am a JS Confirm"),"Alert confirm message is not correct");
        javaScriptAlertPage.alert_clickToDismiss();
        Assert.assertTrue(javaScriptAlertPage.getAlertResult().equals("You clicked: Cancel"),"Alert confirm dismiss result message is not correct");

    }

    @Test
    public void testAlertPromptExample(){
        var javaScriptPage = homePage.clickJavaScriptAlertsLink();
        javaScriptPage.triggerPromptAlert();
        String promptMessage = javaScriptPage.alert_getText();
        Assert.assertTrue(promptMessage.contains("I am a JS prompt"),"Prompt message is not correct");
        javaScriptPage.alert_setInput("Test_message");
        javaScriptPage.alert_clickToAccept();

        Assert.assertTrue(javaScriptPage.getAlertResult().contains("Test_message"),"Promt input is not present in text result");

    }



}
