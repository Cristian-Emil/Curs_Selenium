package ziua_31;

import drivers.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Clasa_dropDown_AutoSugg extends DriverManager {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        WebDriverWait my_wait = new WebDriverWait(driver, Duration.ofSeconds(10));

/*  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
    Aici il comentam pentru ca-l avem defiunit mai sus sub forma de Wait al alertei
*/
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(1000);


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Thread.sleep(1000);
//  Se pune alerta pentru cazul in care apare elementul cautat
//        Alert alertwindow = driver.switchTo().alert();
        Alert alertwindow = my_wait.until(ExpectedConditions.alertIsPresent());

        System.out.println(alertwindow.getText());
        Thread.sleep(500);

//  Fereastra de accesare nu poate fi adresata deci exista varianta sa fie ACCEPTAT sau EXCEPTATA. Alegem accept
        alertwindow.accept();       // acceptam fereastar respectiva si va inchide fereastra utilizand OK button
//        alertwindow.dismiss();       // acceptam fereastar respectiva si va inchide fereastra utilizand CANCEL button
//  Se va afisa modul in care este inchisa fereastra de alerta - de confirmare sau nu.


        Thread.sleep(5000);
        driver.quit();

    }
/*
Doar WebEleemnt pot sa fie inspectate. Din cauza ca acesta fereastra nu este un webelement nu se poate inspecta.
Se poate doar accesa si inchide prin confirmare sau cancel-are

//  Avem optiunea de de a face switch pe acesta ca s-o inchidem:
//        driver.switchTo().alert().accept();

 */


}
