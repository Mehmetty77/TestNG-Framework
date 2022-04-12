package com.techproed.tests.smoketest;

import com.github.javafaker.Faker;
import com.techproed.pages.DefaultPage;
import com.techproed.pages.HotelRoomsPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day12_HotelRoomCreation {
    Faker hotelfaker=new Faker();
    LoginPage loginPage;
    DefaultPage defaultPage;
    HotelRoomsPage hotelRoomsPage;
    @BeforeMethod
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("app_url_login"));
        loginPage=new LoginPage();
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();

        defaultPage=new DefaultPage();
        Assert.assertTrue(defaultPage.addUserButton.isDisplayed());


    }

    @Test
    public void hotelRoomCreate() throws InterruptedException {
//Click on Hotel Management
        defaultPage.hotelManagement.click();
//Click on Hotel Rooms
        defaultPage.hotelRooms.click();
//Click on Add Hotel Room
        hotelRoomsPage=new HotelRoomsPage();
        hotelRoomsPage.addHotelRoomLink.click();
//Enter All required fields
        //ID IS DROPDOWN
        Select select = new Select(hotelRoomsPage.idDropdown);
        select.selectByIndex(2);

        //Code
        hotelRoomsPage.code.sendKeys("discount code");
        hotelRoomsPage.name.sendKeys(hotelfaker.name().fullName());
        hotelRoomsPage.location.sendKeys(hotelfaker.address().fullAddress());
        hotelRoomsPage.textbox.click();
        hotelRoomsPage.textbox.sendKeys(hotelfaker.internet().emailAddress());
        hotelRoomsPage.price.sendKeys("400");

        Select select2 = new Select(hotelRoomsPage.roomTypeDropdown);

        select2.selectByIndex(2);

        hotelRoomsPage.adultCount.sendKeys("2");
        hotelRoomsPage.childCount.sendKeys("2");

        if(!hotelRoomsPage.approvedCheckbox.isSelected()){
            hotelRoomsPage.approvedCheckbox.click();
        }

        hotelRoomsPage.save.click();
        Thread.sleep(2000);

        String actualMessage =hotelRoomsPage.bootbox.getText();

        Assert.assertEquals(actualMessage,"HotelRoom was inserted successfully");

        hotelRoomsPage.okbutton.click();

//To enter a price, we can send keys, OR we can use actions class to drag and drop
//Click Save
//Verify the message: HotelRoom was inserted successfully
//Click OK
    }
}
