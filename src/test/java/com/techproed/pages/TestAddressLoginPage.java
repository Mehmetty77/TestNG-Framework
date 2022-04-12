package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestAddressLoginPage {

    public TestAddressLoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);


    }

    @FindBy(id="session_email")
    public WebElement email;

    @FindBy(xpath = "//input[@id='session_password']")
    public WebElement password;

    @FindBy(xpath = "//input[@name='commit']")
    public WebElement signInButton;


}
