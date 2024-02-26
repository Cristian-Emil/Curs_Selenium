import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// import java.util.Set;

public class Clasa_Browser_Status extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

/*  Am eliminate partea de focalizare pe pagina curenta si deci programul o sa ramana focalizat pe primul browser deschis
    Astfel in clipa in care o sa-l inchidem cu CLOSE o sa se inchida doar browserul pe care e focalizat. Pentru a inchide
    si restul de browsere o sa folosim QUIT.
*/

//  Deschidem al doilea browser:
        driver.findElement(By.linkText("OrangeHRM, Inc")).click();
        Thread.sleep(3000);

//  inchidem primul browser/driver:
        driver.close();     // inchide un singur browser din windows, ultimul pe care sunt focalizati
        Thread.sleep(2000);

//  inchidem restul driverelor si browserul cu pagina la final:
        driver.quit();
    }
}
