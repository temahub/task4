import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MainPage;
import util.ReaderSettings;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import static webdriver.WebBrowserDriverInitialize.*;
import static xpath.XpathShopBy.*;

public class TestRunner {

    private MainPage mainPage;
    private static final String MAIN_PAGE_TITLE = "Торговый портал Shop.by – Все интернет-магазины Минска и Беларуси";
    private static final String PHONE_NUMBER = "447659060";
    private static final String PASSWORD = "testa1qa";

    @BeforeClass
    public void setUp() throws IOException, JSONException {
        initialize();
        mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.mainPage();
    }

    @AfterClass
    public void tearDown(){
        driver.close();
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
    public void randomCatalogSectionSelectionTest() throws NoSuchFieldException, IllegalAccessException{
        mainPage.randomCatalogSectionSelection();
        Field listCatalogField = mainPage.getClass().getDeclaredField("listCatalogDisplayedName");
        Field iField = mainPage.getClass().getDeclaredField("i");
        listCatalogField.setAccessible(true);
        iField.setAccessible(true);

        List<String> listCatalogDisplayedName = (LinkedList<String>) listCatalogField.get(mainPage);
        int i = (int) iField.get(mainPage);
        String catalogName = listCatalogDisplayedName.get(i);

        Assert.assertEquals(catalogName, driver.findElement(By.xpath(catalogNamePage)).getText());
    }

    @Test(priority = 3)
    public void writeToCsvFileTest() throws IOException{
        mainPage.mainPage();
        mainPage.writeToCsvFile();

        Assert.assertTrue(new File(ReaderSettings.csvPath).exists());
    }

    @Test(priority = 4)
    public void logOutTest(){
        mainPage.logOut();

        Assert.assertTrue(driver.findElement(By.xpath(loginButton)).isDisplayed());
    }
}
