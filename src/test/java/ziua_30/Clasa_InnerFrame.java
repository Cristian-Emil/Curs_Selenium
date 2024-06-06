package ziua_30;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_InnerFrame extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();


//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://ui.vision/demo/webtest/frames/");
        Thread.sleep(1000);


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

//  Accesam primul frame
        WebElement frame1 = driver.findElement(By.xpath("//frame[@src='frame_1.html']"));
        driver.switchTo().frame(frame1);
        driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("11111");

//  Ca sa putem lucra in frame-ul 3, adica urmator, trebuie sa ne intoarcem in prima pagina.
        driver.switchTo().defaultContent();

        WebElement frame3 = driver.findElement(By.xpath("//frame[@src='frame_3.html']"));
        driver.switchTo().frame(frame3);
        driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("33333");

//  Din freme-ul 3 o sa trecem in INNER FRAME care este sub frame-ul 3.

//  Ca sa putem lucra in inner frame-ul 3, adica urmator, trebuie sa ne intoarcem in prima pagina.
        driver.switchTo().frame(0);
//  Identificam si apelam radio button-ul cu codul "AB7Lab Id5V1" si facem click pe el pt selectare.
        driver.findElement(By.cssSelector("div.AB7Lab")).click();



        Thread.sleep(5000);
        System.out.println("Au fost inserate toate valorile si acum inchidem site-ul");

        driver.quit();
    }
}
