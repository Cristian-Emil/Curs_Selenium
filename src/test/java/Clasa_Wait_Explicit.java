/*
Ce este un explict wait. Unde, cand si cum se foloseste
 */

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Clasa_Wait_Explicit extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  Adaugam un EXPLICIT WAIT de 10 secunde cu care asteptem pana se incarca pagina cu datele specificate:
        WebDriverWait my_wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
/*  Dupa ce generam EXPLICIT WAIT trebuie sa-l apelam.
    Pentru aceasta anulam liniile anterioare folosite pt driver.findEleemnt (cel utilizate in Clasa_Wait)
 */

//        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        WebElement username = my_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username']")));
        username.sendKeys("Admin");


//        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        WebElement password = my_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']")));
        password.sendKeys("admin123");


        driver.findElement(By.className("oxd-button")).click();
        Thread.sleep(5000);

        driver.quit();

    }
/*
Deci definim/declaram EXPLICIT WAIT o singura data dar il utilizam de cate ori avem nevoie.
El o sa fie apelat in interiorul liniei de comanda de cate ori este nevoie.

Avantaje si dezavantaje:
    - este utilizat in functie de conditiile specificate, va lucra in functie de acestea
    - gaseste elementele incluse
    - o sa astepte pana conditia este adevarata si atunci trece la urmatporul pas


    - trebuie specificat mereu in mod corect pentru a-si face treaba
    - trebuie sa fie scris pentru fiecare element in parte
Performed standard pentru o pagina web este de 10 secunde. Este un timp maxim de asteptare
 */

}
