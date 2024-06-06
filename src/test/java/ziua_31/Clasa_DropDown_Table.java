package ziua_31;//  Cum sa gasim un element dintr-un DROP DOM care nu apare clar pe site

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_DropDown_Table {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

//  ----------------------------------- aici incepem sa scriem programul -----------------------------------------------
//  Accesam pagina si intram pe aceasta.
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        Thread.sleep(1000);
//  Intram pe opagina de PIM

        driver.findElement(By.xpath("//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"][normalize-space()=\"PIM\"]")).click();

//  Drop-down list
        driver.findElement(By.xpath("//div[6]//div[1]//div[2]//div[1]//div[1]//div[1]")).click();
//  Si selectam din DROP DOM - dupa ce eliminam optiunea BLUR - selectam lista elementelor:

//        List<WebElement> lista_ascunsa= driver.findElements(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div[2]//span"));
 /*  in varianta 1 , din tot XPATH-ul primit de la inspection:
        "//*[@id="app"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[6]/div/div[2]/div/div[2]"
        alegem SPAN pt ca exista in toate casutele din subordinea lui.

    in varianta 2 folosim optiunea "listbox" si pe baza ei scriem un XPATH utilizand varinata SPAN : //div[@role='listbox']//span
*/
        List<WebElement> lista_ascunsa= driver.findElements(By.xpath("//div[@role='listbox']//span"));    // avem varianta 2 de verificare a DROP DOM
        Thread.sleep(1000);
        for (WebElement lista:lista_ascunsa)
        {
        System.out.println(lista.getText());            // scriem getText() cva sa ne tipareasca textul si nu codul de scriere.
            if(lista.getText().equals("Finance Manager"))
            {
                lista.click();
                break;
            }
        }

    Thread.sleep(5000);
    driver.quit();
    }
}
