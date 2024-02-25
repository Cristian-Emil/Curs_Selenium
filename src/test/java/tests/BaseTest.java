package tests;

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class BaseTest extends DriverManager {

    ChromeDriver driver;

    @BeforeMethod
    public void before(){

        driver = (ChromeDriver) DriverManager.getDriver();

    }

    @AfterMethod
    public void after(){
        if (driver == null){
            driver.quit();
        }
    }

}