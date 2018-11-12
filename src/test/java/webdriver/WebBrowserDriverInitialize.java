package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class WebBrowserDriverInitialize {
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";

    public static WebDriver driver = null;

    public static void initialize(String browserName){
        if (driver == null){
            if (browserName.equalsIgnoreCase(CHROME)){
                driver = new ChromeDriver();
            }else if (browserName.equalsIgnoreCase(FIREFOX)){
                driver = new FirefoxDriver();
            }else if (browserName.equalsIgnoreCase(IE)){
                driver = new InternetExplorerDriver();
            }

            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
    }

    public void quit(){
        driver.quit();
        driver = null;
    }

    public void close(){
        driver.close();
        driver = null;
    }
}
