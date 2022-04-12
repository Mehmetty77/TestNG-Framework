package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelRoomsPage {

        public HotelRoomsPage() {
            PageFactory.initElements(Driver.getDriver(), this);
        }

        @FindBy(partialLinkText = "ADD HOTELROOM")
        public WebElement addHotelRoomLink;

    @FindBy(id = "IDHotel")
    public WebElement idDropdown;
    @FindBy(id="Code")
    public WebElement code;

    @FindBy(id="Name")
    public WebElement name;
    @FindBy(id="Location")
    public WebElement location;
    @FindBy(id="Price")
    public WebElement price;

    @FindBy(xpath="//textarea[@role='textbox']")
    public WebElement textbox;
    @FindBy(id="IDGroupRoomType")
    public WebElement roomTypeDropdown;

    @FindBy(id="MaxAdultCount")
    public WebElement adultCount;
    @FindBy(id="MaxChildCount")
    public WebElement childCount;

    @FindBy(id="IsAvailable")
    public WebElement approvedCheckbox;

    @FindBy(id="btnSubmit")
    public WebElement save;

    @FindBy(xpath="//div[@class='bootbox-body']")
    public WebElement bootbox;
    @FindBy(xpath="//button[@class='btn btn-primary']")
    public WebElement okbutton;


    }
