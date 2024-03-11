package ziua_29;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_Mouse_Action1 extends DriverManager {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://demo.opencart.com/");

//------------------------------------------- partea de focalizare pe pagina -------------------------------------------
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

//  ----------------------------------- aici incepem sa scriem programul -----------------------------------------------

/*  actiunea unei clase este furnizata de SELENIUM WEBDRIVER

Actiunile care se pot face cu ajutorul MOUSE-ului pe o pagina WEB
Mouse hover     - plasarea sagatii mouse-ului, in general are loc afisare unor drop-down ferestre
Right click     - click dreapta
Double clikc    - click dublu ( are rol de ENTER)
Drag and Drop   - trage si da drumu sau trage si plaseaza

build   - genereaza o actiune
perform - face actiune

*/

        WebElement desktop_ul = driver.findElement(By.xpath("//*[@id=\"narbar-menu\"]/ul/li[1]/a"));
        WebElement mac = driver.findElement(By.xpath("//*[@id=\"narbar-menu\"]/ul/li[1]/div/div/ul/li[2]/a"));
        Thread.sleep(500);
//  Trebuie sa generam o noua actiune :

        Actions actiune = new Actions(driver);

//  Trebuie sa scriem ca mutam mouse-ul pe el si sa construim o actiune BUILD si PERFORM

        actiune.moveToElement(desktop_ul).moveToElement(mac).click().build().perform(); // construieste si face actiunea
//        actiune.moveToElement(desktop_ul).moveToElement(mac).click().perform(); // construieste si face actiunea dar am eliminat BUILD
        Thread.sleep(5000);

//  Eliminam acel fly cloud si dam un click pe el.
        driver.findElement(By.id("turnstile-wrapper")).click();
//        driver.findElement(By.className("ctp-checkbox-label")).click();

/*
    Cum facem RIGHT CLICK - vezi in clasa MOUSE_RIGHTCLICK
 */
    Thread.sleep(5000);
    driver.quit();

    }
}
