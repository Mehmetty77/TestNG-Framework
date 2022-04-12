package com.techproed.homework;

import com.techproed.pages.DefaultPage;
import com.techproed.pages.EditPage;
import com.techproed.pages.LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.JSUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotelListHomework {
    WebDriver driver;
    Select select;
    DateTimeFormatter dtf;
    LoginPage loginPage;
    DefaultPage dp;
    EditPage ep;
    WebDriverWait wait;

    public String switchToNextWindowAndReturnWindowHandle() {
        String currentPageWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String w : allWindowHandles) {
            if (!w.equals(currentPageWindowHandle)) {
                driver.switchTo().window(w);
            }
        }
        return driver.getWindowHandle();
    }

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("app_url_login"));
        loginPage = new LoginPage();
        wait=new WebDriverWait(driver,10);
        ep=new EditPage();
        // Thread.sleep(1000);
//        if (loginPage.advancedLink.isDisplayed()){
        try {
            Thread.sleep(1000);
            loginPage.advancedLink.click();
            Thread.sleep(1000);
            loginPage.proceedLink.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Advanced Link and Proceed Link is not displayed");
        }
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        //Thread.sleep(1000);
        loginPage.loginButton.click();
        Thread.sleep(1000);
        dp = new DefaultPage();
        dp.hotelManagement.click();
        Thread.sleep(1000);
        dp.hotelList.click();
        Thread.sleep(1000);

    }

    @Test(priority = 1)
    public void checkRoomType1() throws InterruptedException {
        select = new Select(dp.IDGroup);
        select.selectByVisibleText("Hotel Type1");
        dp.searchButton.click();
        TimeUnit.MILLISECONDS.sleep(1000);
        List<WebElement> listOfHotelType = driver.findElements(By.xpath("//table/tbody/tr/td[7]"));
        for (WebElement w : listOfHotelType) {
            Assert.assertEquals(w.getText(), "Hotel Type1");
        }
    }

    @Test(priority = 2)
    public void checkRoomType2() throws InterruptedException {
        select = new Select(dp.IDGroup);
        select.selectByVisibleText("Hotel Type2");
        dp.searchButton.click();
        TimeUnit.MILLISECONDS.sleep(500);
        List<WebElement> listOfHotelType = driver.findElements(By.xpath("//table/tbody/tr/td[7]"));
        for (WebElement w : listOfHotelType) {
            Assert.assertEquals(w.getText(), "Hotel Type2");
        }
    }

    @Test(priority = 3)
    public void checkRoomTypeClear() throws InterruptedException {
        dp.clearButton.click();
        TimeUnit.MILLISECONDS.sleep(500);
        List<WebElement> listOfHotelType = driver.findElements(By.xpath("//table/tbody/tr/td[7]"));
        List<String> condensedListString = listOfHotelType.stream().map(WebElement::getText).distinct().collect(Collectors.toList());
        Assert.assertTrue(condensedListString.size() >= 2);
    }

    @Test(priority = 4)
    public void checkDateFormat() {
        String date = dp.date.getText();
        dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDateTime currentDate = LocalDateTime.now();
        Assert.assertEquals(date, dtf.format(currentDate));
    }

    @Test(priority = 5)
    public void checkNumberOfRecords() {
        select = new Select(dp.viewNumber);
        String selectedViewOpt = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedViewOpt, "10");

        select.selectByValue("20");
        selectedViewOpt = select.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedViewOpt, "20");

        WebElement viewNumberInput = dp.viewRecordPage;
        String numberOfPageOnRecords = viewNumberInput.getAttribute("value");
        int expectedNumberOfPageOfRecordsInteger = Integer.parseInt(numberOfPageOnRecords) + 1;
        String expectedNumberOfPageOfRecords = String.valueOf(expectedNumberOfPageOfRecordsInteger);

        dp.nextRecords.click();

        viewNumberInput = dp.viewRecordPage;
        wait.until(ExpectedConditions.textToBePresentInElementValue(viewNumberInput, expectedNumberOfPageOfRecords));
        numberOfPageOnRecords = viewNumberInput.getAttribute("value");
        Assert.assertEquals(numberOfPageOnRecords, expectedNumberOfPageOfRecords);
    }

    @Test(priority = 6)
    public void checkDownload() throws InterruptedException {
        dp.downloadButton.click();
        String downloadPath = "C:\\Users\\MEHMETTY\\Downloads\\Admin - List Of Hotels.xlsx";
        TimeUnit.MILLISECONDS.sleep(500);
        Assert.assertTrue(Files.exists(Paths.get(downloadPath)));
    }

    @Test(priority = 7)
    public void checkEditHotel() {
        dp.detailsButton.click();
        switchToNextWindowAndReturnWindowHandle();
        Assert.assertEquals(driver.getTitle(), "Admin - Edit Hotel");
        ep.code.clear();
        ep.code.sendKeys("10151");
        JSUtils.clickElementByJS(ep.saveButton);
        wait.until(ExpectedConditions.visibilityOf(ep.successMessage));
        Assert.assertEquals(ep.successMessage.getText(), "Hotel was updated successfully");
        ep.popUpOkButton.click();
    }

    @Test(priority = 8)
    public void checkUpdateDelete() throws InterruptedException {
        String confirmMessage = "Would you like to continue?";
        String errorMessage = "Error: Couldn't delete hotel : please delete reservations for this hotel first";

        dp.detailsButton.click();
        switchToNextWindowAndReturnWindowHandle();
        Thread.sleep(500);
        ep.propertiesRoute.click();
        Thread.sleep(500);
        ep.propertyCodeUpdate.clear();
        ep.propertyCodeUpdate.sendKeys("10149");
        ep.propertyUpdateButton.click();
        wait.until(ExpectedConditions.visibilityOf(ep.propertyPagePopUpMessage));
        Assert.assertTrue(ep.propertyPagePopUpMessage.isDisplayed());
        ep.propertyPageMessageOKButton.click();
        ep.propertyDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(ep.propertyPagePopUpMessage));
        Assert.assertEquals(ep.propertyPagePopUpMessage.getText(), confirmMessage);
        ep.propertyPageMessageOKButton.click();
        wait.until(ExpectedConditions.textToBePresentInElement(ep.propertyPagePopUpMessage, errorMessage));
        Assert.assertEquals(ep.propertyPagePopUpMessage.getText(), errorMessage);
        ep.propertyPageMessageOKButton.click();
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}