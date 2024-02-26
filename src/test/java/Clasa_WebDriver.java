// Cum putem utiliza o clasa care foloseste WEBDRIVER - explicatii si instructiuni

/*
Folosim variante
1. In clasa in care utilizam webdriver inseram linia care sa contina calea spre locatie si apeleaza functia aleasa

In aceasta clasa o sa utilizam aceasta cale direct din linia de comanda
System.setProperty("webdriver.chrome.driver", "C:/Users/CRISTIAN ZIDARESCU/IdeaProjects/- driver Selenium-Maven/ChromeDriver/chromedriver.exe")

 */

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Clasa_WebDriver {

    public static void main(String[] args)throws InterruptedException {
//  Se introduce functia throws InterruptedException pentru a putea sa utilizam comanda care opreste executia acesteia
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/CRISTIAN ZIDARESCU/IdeaProjects/- driver Selenium-Maven/ChromeDriver/chromedriver.exe");

//  Utilizam varinata de WebDrivere si sa se creeze un obiect ChromeDriver
        WebDriver driver = new ChromeDriver();
//  Deci a fost creat un obiect care apeleaza driver-ul pe care il specificam


//  Verificam versiunea ChromeDriver utilizata pentru WebDriver-ul nostru
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String chromeVersion = caps.getBrowserVersion();

//  Afișează versiunea ChromeDriver
        System.out.println("Versiunea ChromeDriver instalată este: " + chromeVersion +
                "\n" + "Astfel am creat si apelat o noua instanta de WebDriver" );


/*  In general cand se creaza un obiect ChromeDrive se instantiaza o clasa CHROMEDRIVER object. Cand se utilizeaza WebDriver
    se creaza un obiect care poate sa fie pentru oricare clasa  - ChromeDriver, EdgeDriver , FoxDriver sau alt driver
    Cand folosim mai multe genuri de drivere este bine sa utilizam WebDriver
*/

        Thread.sleep(5000);
//  Linia de mai sus opreste executia liniilor de comanda timp de 10 secunde / 10.000 milisecunde

/*
    ATENTIE - INDIFERENT ca folosinm ChromeDRIVER, WebRIVER sau alt Driver o sa folosim mereu termenul "new" pentru a creea
    o noua instanta a unei clase si a apela constructorul clasei respective


    Deci :
    WebDriverManager.chromedriver().setup();
    nu este o componenta individuala a Selenium. Modificarea a fost facuta intre timp si componenta de manager este acum
    parte a WebDriver, deci nu este nevoie sa mai fie apelata in mod special

ESTE BINE SA NU FOPLOSIM UN DRIVER/BROWSER SPECIFIC - in acest ca este chromedriver

    Avem obligatia sa facem up-date pentru driver si selenium la ultima versiune aparuta. Aici folosim chromedriver.

 */

        driver.close();
        driver.quit();
    }

}


