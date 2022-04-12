package com.techproed.pages;
import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DefaultPage {
    public DefaultPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//span[@class='hidden-480']")
    public WebElement addUserButton;

    @FindBy(id = "menuHotels")
    public WebElement hotelManagement;

    @FindBy(partialLinkText = "Hotel Rooms")
    public WebElement hotelRooms;

    @FindBy(xpath = "//span[@class='hidden-480']")
    public WebElement addHotelRoomButton;

    @FindBy(partialLinkText = "Hotel List")
    public WebElement hotelList;

    @FindBy(id = "lkpGroups")
    public WebElement IDGroup;

    @FindBy(xpath = "//table/thead/tr[2]/td[8]/div/button")
    public WebElement searchButton;

    @FindBy(xpath = "//table/thead/tr[2]/td[8]/button")
    public WebElement clearButton;

    @FindBy(name = "datatable_ajax_length")
    public WebElement viewNumber;

    @FindBy(xpath = "//a[@title='Next']")
    public WebElement nextRecords;

    @FindBy(xpath = "//a[@title='Prev']")
    public WebElement prevRecords;

    @FindBy(xpath = "//input[@type='text' and @maxlenght='5']")
    public WebElement pageRecord;

    @FindBy(id = "btnExportTable")
    public WebElement downloadButton;

    @FindBy(xpath = "//table/tbody/tr[1]/td[8]/a")
    public WebElement detailsButton;

    @FindBy(tagName = "small")
    public WebElement date;

    @FindBy(xpath = "(//input[@maxlenght='5'])[2]")
    public WebElement viewRecordPage;

    @FindBy(xpath = "//span[contains(@class, 'username')]")
    public WebElement userID;



}
