package ziua_41;

/*
1.  Functionality
2.  Prepare test data in excel
3.  Develope automation script

*/

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Clasa_DataDrivenTesting extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver=new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
        Thread.sleep(3000);

        WebElement casuta_nedorita = driver.findElement(By.id("wzrk-cancel"));
//                                                      By.xpath("//*[@id="wzrk-cancel"]")
        casuta_nedorita.click();






    Thread.sleep(5000);
    driver.close();
    driver.quit();
    }
}
