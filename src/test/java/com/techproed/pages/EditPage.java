package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditPage {

    public EditPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "Code")
    public WebElement code;

    @FindBy(xpath = "(//button[.='Save'])[1]")
    public WebElement saveButton;

    @FindBy(className = "bootbox-body")
    public WebElement successMessage;

    @FindBy(xpath = "//button[.='OK']")
    public WebElement popUpOkButton;

    @FindBy(linkText = "Properties")
    public WebElement propertiesRoute;

    @FindBy(id = "product_barcodeCode_101")
    public WebElement propertyCodeUpdate;

    @FindBy(xpath = "//table/tbody/tr/td[6]/a[2]")
    public WebElement propertyUpdateButton;

    @FindBy(xpath = "//div[@class='bootbox-body']")
    public WebElement propertyPagePopUpMessage;

    @FindBy(xpath = "//button[.='OK']")
    public WebElement propertyPageMessageOKButton;

    @FindBy(id = "btnDelete")
    public WebElement propertyDeleteButton;





}


