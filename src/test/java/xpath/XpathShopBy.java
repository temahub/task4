package xpath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static webdriver.WebBrowserDriverInitialize.driver;

public class XpathShopBy {

    public static WebElement phoneSelector = driver.findElement(By.xpath("//a[@id='phone-tab']"));
    public static WebElement passwordField = driver.findElement(By.xpath("//input[@id='LLoginForm_password']"));
    public static WebElement submitLoginButton = driver.findElement(By.xpath
            ("//input[@class='Page__ActiveButtonBg Header__ButtonLogIn']"));

    public static String phoneField = "//input[@id='LLoginForm_phone']";
    public static String loginButton = "//span[@class='Header__LoginLinkAuth Page__SelectOnBg Header__LinkShowWapper']";
    public static String userNameField = "//div[@class='Header__BlockNameUser']";
    public static String catalogLinks = "//div[contains(@class, \"Catalog__SectionMainBlock\")]";
    public static String catalogNamePage = "//span[@class='BreadCrumbs__LastElement']/span[1]";
    public static String userSettingsTriangle = "//div[@class='chzn-btn glyphicon']";
    public static String userLogOutButton = "//a[@id='yt0']";
}
