package steps;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;

public class RegForm {
    private static final String WEBURL = "https://colorlib.com/etc/regform/colorlib-regform-4/";
    //    "http://demo.testingchief.com/";
    private static final boolean RUN_GRID = true;
    private static final String HUB_URL = "http://192.168.0.23:4444/wd/hub";

    @Test(dataProvider = "data")
    public void test(String firstName) throws InterruptedException, MalformedURLException {
        WebDriver driver;

        if (RUN_GRID) {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.LINUX);
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
        } else {
//            WebDriverManager.chromedriver().driverVersion("84.0.4147.30").setup();
            WebDriverManager.getInstance(FIREFOX).setup();
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(WEBURL);
        driver.findElement(By.name("first_name")).sendKeys(firstName);
        driver.findElement(By.name("last_name")).sendKeys("Smith");
        driver.findElement(By.name("birthday")).sendKeys("01/01/1980");
        driver.findElement(By.className("radio-container")).click();
        driver.findElement(By.name("email")).sendKeys(firstName + "@test.com");
        driver.findElement(By.name("phone")).sendKeys("555-555-1234");
        System.out.println("Registered " + firstName);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class='btn btn--radius-2 btn--blue']")).click();
        driver.quit();
    }

    @DataProvider(name = "data", parallel = true)
    public Object[][] dataProvider() {
        return new Object[][]{{"Ada"}, {"Adelaide"}, {"Adeline"}, {"Alice"}, {"Amara"},
//                {"Amelia"}, {"Anastasia"}, {"Anna"}, {"Arabella"}, {"Aria"},
//                {"Astrid"}, {"Audrey"}, {"Aurelia"}, {"Aurora"}, {"Ava"},
//                {"Charlotte"}, {"Chloe"}, {"Claire"}, {"Clara"}, {"Cora"},
//                {"Eleanor"}, {"Elizabeth"}, {"Elodie"}, {"Eloise"}, {"Emma"},
//                {"Esme"}, {"Evangeline"}, {"Evelyn"}, {"Freya"}, {"Genevieve"},
//                {"Hazel"}, {"Imogen"}, {"Iris"}, {"Isabella"}, {"Isla"},
//                {"Ivy"}, {"Jane"}, {"Lilith"}, {"Lucy"}, {"Luna"},
//                {"Maeve"}, {"Maisie"}, {"Maya"}, {"Mia"}, {"Nora"},
                {"Olivia"}, {"Ophelia"}, {"Penelope"}, {"Rose"}, {"Violet"}};
    }
}
