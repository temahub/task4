package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import util.ReaderSettings;

import static xpath.XpathShopBy.*;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
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
}
