package ziua_27;/*
Se explica modul in care generam FLUENT WAIT si apoi il apelam.
 */

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;



public class Clasa_Wait_Fluent extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  Adaugam un FLUENT WAIT de 10 secunde cu care asteptem pana se incarca pagina cu datele specificate:
        FluentWait my_wait = new FluentWait(driver);
        my_wait.withTimeout(Duration.ofSeconds(40L));
        my_wait.pollingEvery(Duration.ofSeconds(10L));
        my_wait.ignoring(NoSuchElementException.class);


        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

/*  Dupa ce declaram/generam FLUENT WAIT trebuie sa-l apelam.
    Pentru aceasta anulam liniile anterioare folosite pt driver.findEleemnt (cel utilizate in ziua_23.Clasa_Wait)
*/

        WebElement username = (WebElement) my_wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@placeholder='Username']")));
            username.sendKeys("Admin");


        WebElement password= (WebElement) my_wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@placeholder='Password']")));
            password.sendKeys("admin123");


        driver.findElement(By.className("oxd-button")).click();
        Thread.sleep(5000);

        driver.quit();

    }
/*
Partea de declarare FLUENT WAIT se poate copia de pe pagina oficiala a selenium.de
https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/FluentWait.html


Forma initiala a fost modificat pt ca dadea eroare:

 Wait<WebDriver> my_wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(40L))
                .pollingEvery(Duration.ofSeconds(10L))
                .ignoring(NoSuchElementException.class);


        WebElement password = my_wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.xpath("//input[@placeholder='Password']"));
            }
        });
        password.sendKeys("admin123");


 */

}
