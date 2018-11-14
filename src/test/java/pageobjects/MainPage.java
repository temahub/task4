package pageobjects;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ReaderSettings;
import xpath.XpathShopBy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static webdriver.WebBrowserDriverInitialize.driver;
import static xpath.XpathShopBy.*;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void mainPage(){
        driver.get(ReaderSettings.urlName);
    }

    public void loginByPhone(String phoneNumber, String password){

        Actions ob = new Actions(driver);
        ob.moveToElement(loginButton);
        ob.click(loginButton);
        Action action  = ob.build();
        action.perform();

        phoneSelector.click();
        phoneField.clear();
        phoneField.sendKeys(phoneNumber);
        passwordField.clear();
        passwordField.sendKeys(password);
        submitLoginButton.click();
    }

    public void randomCatalogSectionSelection(){
        Random random = new Random();
        List<WebElement> listCatalogDisplayed = new LinkedList<>();
        List<String> listCatalogDisplayedName = new LinkedList<>();
        List<WebElement> catalogLinks = driver.findElements(By.xpath(XpathShopBy.catalogLinks));
        for (WebElement w : catalogLinks
        ) {
            if (w.isDisplayed()){
                listCatalogDisplayed.add(w);
                listCatalogDisplayedName.add(w.getText());
            }
        }
        int i = random.nextInt(listCatalogDisplayed.size());
        System.out.println(listCatalogDisplayedName.get(i));
        listCatalogDisplayed.get(i).click();
    }

    public void writeToCsvFile() throws IOException {

        String commentProducts = driver.getPageSource();

        String matcher =
                "<a class=\"\"ModelReviewsHome__IconBlockModel\"\" href=\"\"\\X+\"\" data-model=\"\"\\X+\"\"><img src=\"\"\\X+\"\" srcset=\"\"\\X+\"\" alt=\"\"\\X+\"\" title=\"\"(\\X+)\"\" \\/><\\/a>";

        Pattern p = Pattern.compile(matcher);
        Matcher m = p.matcher(commentProducts);
        commentProducts = m.replaceAll("");


        File file = new File(ReaderSettings.csvPath);
        file.createNewFile();
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(ReaderSettings.csvPath));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord(commentProducts);


        //System.out.println(commentProducts);
    }


}
