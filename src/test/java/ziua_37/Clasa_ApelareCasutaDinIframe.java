package ziua_37;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Clasa_ApelareCasutaDinIframe {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");

        // Comutăm la iframe-ul corect
        driver.switchTo().frame(0);

        // Utilizăm WebDriverWait pentru a ne asigura că elementul este vizibil
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nume = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        // Inserăm textul dorit în caseta "Name"
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('value','Vasile al lui Ion')", nume);

        // O altă metodă de a insera text folosind sendKeys
        // nume.sendKeys("Vasile al lui Ion");

        // Așteptăm puțin pentru a vedea schimbarea (opțional)
        Thread.sleep(3000);

        driver.close();
        driver.quit();
    }
}
