import org.json.JSONException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MainPage;
import util.ReaderSettings;
import webdriver.WebBrowserDriverInitialize;

import java.io.IOException;

import static webdriver.WebBrowserDriverInitialize.*;
import static xpath.XpathShopBy.*;

public class TestRunner {

    private MainPage mainPage;
    private static final String MAIN_PAGE_TITLE = "Торговый портал Shop.by – Все интернет-магазины Минска и Беларуси";
    private static final String PHONE_NUMBER = " 447659060";
    private static final String PASSWORD = "testa1qa";

    @BeforeClass
    public void setUp() throws IOException, JSONException {
        initialize();
        mainPage = new MainPage(driver);
        mainPage.mainPage();
    }

    @Test(priority = 0)
    public void mainPageTest(){
        Assert.assertEquals(MAIN_PAGE_TITLE, driver.getTitle());
    }

    @Test(priority = 1)
    public void loginByPhoneTest(){
        mainPage.loginByPhone(PHONE_NUMBER, PASSWORD);
        Assert.assertTrue(driver.findElement(By.xpath(userNameField)).isDisplayed());
    }

    @Test(priority = 2)
    public void randomCatalogSectionSelection(){
        mainPage.randomCatalogSectionSelection();

    }

    @Test
    public void writeToCsvFileTest() throws IOException{
        mainPage.writeToCsvFile();
    }




    @AfterClass
    public void tearDown(){
        driver.close();
    }
}
