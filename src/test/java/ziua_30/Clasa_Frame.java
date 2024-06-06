package ziua_30;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_Frame extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();


//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://www.selenium.dev/selenium/docs/api/java/index.html?overview-summary.html");
        Thread.sleep(1000);


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//  ne mutam de pe pagina principala pe frameul 1 - daca acesta e activ
//        driver.switchTo().frame("packageListFrame");
        driver.findElement(By.linkText("org.openqa.selenium")).click();
        Thread.sleep(1000);

        driver.switchTo().defaultContent();     // ne mutam inapoi pe prima pagina

//  ne mutam pe frameul 2  - daca acesta e activ
//       driver.switchTo().frame("packageFrame");
        driver.findElement(By.linkText("WebDriver")).click();
        Thread.sleep(1000);

        driver.switchTo().defaultContent();     // ne mutam inapoi pe prima pagina

//  ne mutam pe frameul 3 - daca acesta e activ si dam click pe "Instance Methods"
//       driver.switchTo().frame("classFrame");
        driver.findElement(By.xpath("//*[@id=\"method-summary-table-tab2\"]")).click();
        Thread.sleep(1000);



        Thread.sleep(5000);
        driver.quit();
    }
}
