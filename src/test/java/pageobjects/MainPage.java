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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static xpath.XpathShopBy.*;

public class MainPage {

    private WebDriver driver;
    private int i;
    private List<String> listCatalogDisplayedName = new LinkedList<>();
    private WebDriverWait wait;

    public MainPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void mainPage(){
        driver.get(ReaderSettings.urlName);
    }

    public void loginByPhone(String phoneNumber, String password){
        WebElement loginB = driver.findElement(By.xpath(loginButton));
        Actions ob = new Actions(driver);
        ob.moveToElement(loginB);
        ob.click(loginB);
        Action action  = ob.build();
        action.perform();

        driver.findElement(By.xpath(phoneSelector));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(phoneField)));
        WebElement phoneFieldWeb = driver.findElement(By.xpath(phoneField));
        phoneFieldWeb.click();
        phoneFieldWeb.sendKeys(phoneNumber);
        driver.findElement(By.xpath(passwordField)).sendKeys(password);
        driver.findElement(By.xpath(submitLoginButton)).click();
    }

    public void logOut(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath
                (userNameField)));
        driver.findElement(By.xpath(userSettingsTriangle)).click();
        driver.findElement(By.xpath(userLogOutButton)).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath
                (loginButton)));
    }

    public void randomCatalogSectionSelection(){
        Random random = new Random();
        List<WebElement> listCatalogDisplayed = new LinkedList<>();

        List<WebElement> catalogLinks = driver.findElements(By.xpath(XpathShopBy.catalogLinks));
        for (WebElement w : catalogLinks
        ) {
            if (w.isDisplayed()){
                listCatalogDisplayed.add(w);
                listCatalogDisplayedName.add(w.getText());
            }
        }
        i = random.nextInt(listCatalogDisplayed.size());
        listCatalogDisplayed.get(i).click();
    }

    public void writeToCsvFile() throws IOException {
        List<String> list = new ArrayList<>();
        //String regex = "<a class=\"ModelReviewsHome__IconBlockModel\".*><img.+ title=\"(?<Gru>.+)\".*/>.*</a>";
        String regex = "<a class=\"ModelReviewsHome__NameModel\".*#desc\">(?<Gru>.+)</a><a";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(driver.getPageSource());

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                list.add(matcher.group(i));
            }
        }

        BufferedWriter writer = Files.newBufferedWriter(Paths.get(ReaderSettings.csvPath));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        for (String s : list
             ) {
            csvPrinter.printRecord(s);
            System.out.println("Товар:");
            System.out.println(s);
        }
        csvPrinter.flush();

        writer.close();
    }
}
