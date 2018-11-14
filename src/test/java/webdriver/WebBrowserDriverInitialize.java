package webdriver;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import util.ReaderSettings;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebBrowserDriverInitialize {
    private static final String SETTINGS = "src/test/resources/settings.JSON";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";

    public static WebDriver driver = null;

    public static void initialize() throws IOException, JSONException {
        ReaderSettings.readSettingsFromJSONFile(SETTINGS);
        if (driver == null){
            if (ReaderSettings.browserName.equalsIgnoreCase(CHROME)){
                driver = new ChromeDriver();
            }else if (ReaderSettings.browserName.equalsIgnoreCase(FIREFOX)){
                driver = new FirefoxDriver();
            }else if (ReaderSettings.browserName.equalsIgnoreCase(IE)){
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
