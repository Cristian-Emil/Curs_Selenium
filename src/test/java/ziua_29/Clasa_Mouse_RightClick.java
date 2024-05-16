/*
Cum facem un click dreapta si cum il initializam.
Right click - contextClick(element)
 */
package ziua_29;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_Mouse_RightClick extends DriverManager {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

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
/*
    Right click - contextClick(elementul_apasat).perform()
*/

//  Cautam elementul "BUTTON" pe care trebuie sa facem click dreapta :
        WebElement butonul = driver.findElement(By.xpath("/html/body/div/section/div/div/div/p/span"));
//                                   By.xpath -ul din exercitiu este //span[@class='content-menu-one btn btn-neutral']
        Thread.sleep(500);
        Actions actiune = new Actions(driver);

/*  Dam click dreapta - RIGHT CLICK, este doar o ACTIUNE , deci ea doar genereaza actiunea nu o si initializeaza.
Pentru a realiza actiunea se adauga linia unde se defineste "driver.findElement" si ii punem actiunea click normal
 */
        actiune.contextClick(butonul).perform();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/ul/li[3]")).click();

        Thread.sleep(2500); // se pune doar ca sa vedem fereastra care apare.
        driver.switchTo().alert().accept(); //  inchidem fereastra de alerta.
        Thread.sleep(500);

    Thread.sleep(3500);
    driver.quit();

    }
}
