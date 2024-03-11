package ziua_29;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_Mouse_RightClick extends DriverManager {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://swisnl.github.io/JQuery-contextMenu/demo.html/");

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

//  Cautam elementul "BUTTON" pe care trebuie sa facem click dreapta :
        WebElement button = driver.findElement(By.xpath(""));



    Thread.sleep(5000);
    driver.quit();

    }
}
