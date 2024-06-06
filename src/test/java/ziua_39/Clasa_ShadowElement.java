package ziua_39;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_ShadowElement {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
//  am mutat de aici linia de maximizare ecran , vezi liniile de mai jos

        driver.get("https://books.pwakit.appspot.com");

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

//  Aceste element este in interiorul singurului shadow DOM

        String cssSelectorForHost1 = "book-app[apptitle='Books']";
        Thread.sleep(2500);

        SearchContext umbra = driver.findElement(By.cssSelector("book-app[apptitle='Books'")).getShadowRoot();
        Thread.sleep(2000);
        umbra.findElement(By.cssSelector("#input")).sendKeys("Bun venit");

    Thread.sleep(5000);
    driver.close();
    driver.quit();
    }
}
