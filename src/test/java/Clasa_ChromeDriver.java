// Cum putem utiliza o clasa care foloseste CHROMEDRIVER

/*
Folosim variante
1. In clasa in care utilizam chromedriver inseram linia care sa contina calea spre locatie si apeleaza functia aleasa

In aceasta clasa o sa utilizam aceasta cale direct din linia de comanda
System.setProperty("chromedriver.driver", "C:/Users/CRISTIAN ZIDARESCU/IdeaProjects/- driver Selenium-Maven/ChromeDriver/chromedriver.exe")
 */

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Clasa_ChromeDriver {

    public static void main(String[] args) throws InterruptedException {
        // Setează calea către fișierul executabil ChromeDriver
        System.setProperty("chromedriver.driver",
                "C:/Users/CRISTIAN ZIDARESCU/IdeaProjects/- driver Selenium-Maven/ChromeDriver/chromedriver.exe");

//  Crează o instanță a obiectului ChromeOptions
        ChromeOptions options = new ChromeOptions();

//  Creează o instanță a obiectului ChromeDriver cu opțiunile specificate
        ChromeDriver driver = new ChromeDriver(options);

//  Obține versiunea ChromeDriver
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String chromeVersion = caps.getBrowserVersion();

//  Afișează versiunea ChromeDriver
        System.out.println("Versiunea ChromeDriver instalată este: " + chromeVersion +
                "\n" + "Astfel am creat si apelat o noua instanta de ChromeDriver");

        Thread.sleep(5000);

//  Închide browserul
        driver.quit();

/*  O alta varianta este sa folosim WebDriver si astfel in loc de ChromeDriver scriem linia care utlizeaza o instanta
/   a obiectului ChromeDriver */

//  Pentru cum se utilizeaza varianta  ... WebDriver driver = new ChromeDriver(); ...  vezi clasa Clasa_cu_WebDriver

/*
    ATENTIE - INDIFERENT ca folosinm ChromeDRIVER, WebRIVER sau alt Driver o sa folosim mereu termenul "new" pentru a creea
    o noua instanta a unei clase si a apela constructorul clasei respective

    Avem obligatia sa facem up-date pentru driver si selenium la ultima versiune aparuta. Aici folosim chromedriver.
*/

    }

}
