package xpath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static webdriver.WebBrowserDriverInitialize.driver;

public class XpathShopBy {

    public static WebElement loginButton = driver.findElement(By.xpath
            ("//span[@class='Header__LoginLinkAuth Page__SelectOnBg Header__LinkShowWapper']"));
    public static WebElement phoneSelector = driver.findElement(By.xpath("//a[@id='phone-tab']"));
    public static WebElement phoneField = driver.findElement(By.xpath("//input[@id='LLoginForm_phone']"));
    public static WebElement passwordField = driver.findElement(By.xpath("//input[@id='LLoginForm_password']"));
    public static WebElement submitLoginButton = driver.findElement(By.xpath
            ("//input[@class='Page__ActiveButtonBg Header__ButtonLogIn']"));
    public static WebElement allCatalog = driver.findElement(By.xpath
            ("//div[@class='Header__BlockCatalogLink']/a[@class='Header__CatalogAllLink' and 1]"));
}
