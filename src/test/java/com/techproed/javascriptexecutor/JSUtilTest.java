package com.techproed.javascriptexecutor;

import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.JSUtils;
import com.techproed.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JSUtilTest {

    @Test
    public void scrollIntoView(){
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));

        WebElement resentBlog = Driver.getDriver().findElement(By.xpath("//*[.='Recent Blog']"));
        JSUtils.scrollIntoViewJS(resentBlog);
        ReusableMethods.waitFor(3);

        Assert.assertEquals(resentBlog.getText(),"Recent Blog");


    }

    @Test
    public void clickWithJS(){
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));

        WebElement checkAvailability=Driver.getDriver().findElement(By.xpath("//input[@value='Check Availability']"));

        JSUtils.clickElementByJS(checkAvailability);



    }

}
