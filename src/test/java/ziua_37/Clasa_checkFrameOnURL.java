package ziua_37;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Clasa_checkFrameOnURL {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");

        // Obține toate iframe-urile din pagină
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        boolean elementFound = false;
        WebElement nume = null;

        for (int i = 0; i < iframes.size(); i++) {
            driver.switchTo().frame(i);
            try {
                nume = driver.findElement(By.xpath("//*[@id=\"name\"]"));
                elementFound = true;
                System.out.println("Element found in iframe index: " + i);
                break;
            } catch (Exception e) {
                driver.switchTo().defaultContent(); // Revino la contextul principal
            }
        }

        if (elementFound) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('value','Vasile al lui Ion')", nume);
        } else {
            System.out.println("Element not found in any iframe.");
        }

        driver.quit();
    }



}
