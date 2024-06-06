package ziua_32;// Tabele dinamice - valorile variaza in timpo si aranjament. O sa folosim clasa ziua_27.Clasa_PaginationTable
//  Pentru ca avem nevoie o sa importam valorile din aceasta clasa, clasa care extinde DriverManeger

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ziua_33.Clasa_PaginationTable;

import java.time.Duration;
import java.util.Set;


public class Clasa_HandleTable_2 extends Clasa_PaginationTable {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://demo.opencart.com/admin/");
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
/*  ---------------------- aici incepem sa scriem cerintele programului ------------------------------------------------

- gasiti numarul total de pagini

 */
/*  Prima dat accesam pagina si inseram valorile pentru USERNAME si PASSWORD - demon pentru ambele
    Ne apare un element de genul CLOUDFLARE pe care-l inchidem bifand casuta ca sa intram pe pagina.
 */

        driver.findElement(By.xpath("(//input[@id='input-username'])[1]")).sendKeys("demo");
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//input[@id='input-password'])[1]")).sendKeys("demo");
        Thread.sleep(1000);
        driver.findElement(By.tagName("button")).click();
        Thread.sleep(1000);

//  Acesta este elementul de tip CLOUDFLARE - dam intai un click pe titlu ca sa fim siguri ca suntem pe pagina
//        driver.findElement(By.className("zone-name-title h1")).click();
        driver.findElement(By.xpath("//*[@id=\"challenge-stage\"]/div/label/span[1]")).click();
        Thread.sleep(1000);

//  Ne apare o fereastra de confirmare pe care o inchidem dand click pe X-ul din dreapat sus al ei
        driver.findElement(By.xpath("//*[@id=\"modal-security\"]/div/div/div[1]/button")).click();
        Thread.sleep(1000);

//  In stanga ne ducem pe fereastra si dam click pe Customers
        driver.findElement(By.xpath("//*[@id=\"menu-customer\"]/a")).click();
        Thread.sleep(1000);

//  In drop down-ul aparut avem un alt nivel de Customers si dam click si pe acesta
        driver.findElement(By.xpath("//*[@id=\"collapse-5\"]/li[1]/a")).click();
        Thread.sleep(1000);

        String text=driver.findElement(By.xpath("//*[@id=\"form-customer\"]/div[2]/div[2]")).getText();
        System.out.println("Numarul total de clinti si de pagini prezentate =  " + text);

/*  Apare un Customer list cu diverse date ca Nume, E-mail, Customer Group, etc.. Observam ca avem cateva sute de pagini
    Trebuie sa vedem care este numarul total de pagini. Tabelul este dinamic.
*/
//  Partea de testare o gasim in clasa ..... "ziua_27.Clasa_PaginationTable.java" . Aici mo sa facem doar verificarea nr de pagini



    Thread.sleep(5000);
    driver.quit();

    }
}
