package frame;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WYSIWYGEditorTests extends BaseTest {

    @Test
    public void testToEditInWYSIWYGEditorTests(){

        var editorPage = homePage.clickWYSIWYGEditorLink();
        String text1 = "Hello ";
        String text2 = "World";

            editorPage.cleanEditor();
            editorPage.enterTextInEditor(text1);
            editorPage.clickIncreaseIndentButton();
            editorPage.enterTextInEditor(text2);

        Assert.assertEquals(editorPage.getEditorText(),text1 + text2 ,"Text in editor is not matching");

    }
}
