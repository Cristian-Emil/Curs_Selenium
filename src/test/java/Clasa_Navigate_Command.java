/*
Comenzi de navigare pe paginile web si site
 */

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Clasa_Navigate_Command extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
//  Folosim un IMPLICIT wait de 5 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));

        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(2000);

        driver.get("https://www.flipkart.com/");
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(2000);

//  Aici avem focalizarea pe pagina curenta:
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

    System.out.println('\n');                       // tiparim un rand liber
/*
Comenzile de navigare sunt :

    navigate().to(url)      - este identica cu driver.get(url)
    navigate().forward()    - ne mutam inainte pe urmatoare pagina
    navigate().bacK()       - ne muta pe pagina anterioara
    navigate().refresh()    - reimprospatam pagina pe care am intrat


La utilizarea URL putem sa avem formatul:

    "https://www.google.com" - - - > string
    URL myURL = newURL ("https://www.google.com") deci avem o noua instanta de URL

    navigate().to(URL)  --->    accepta url si format URL sau String
    driver.get()        --->    accepta doar format String
*/


/*
Exercitiu pt forward, back si refresh:
    Dupa ce am intrat pe pagina de ORANGEHRM ne mutam pe pagina de Flipkart. Ca sa ne intoarcem la Orange folosim BACK
    si tiparim noul URL pe ecran
*/
        driver.navigate().back();
        System.out.println(driver.getCurrentUrl());

//  Ne intoarcem pe pagina a doua si o tiparim. Intoarcerea se face cu comanda FORWARD :
        driver.navigate().forward();
        System.out.println(driver.getCurrentUrl());

//  Ne intoarcem pe prima pagina si facem refresh la pagina s-o reimprospatam
        driver.navigate().back();
        driver.navigate().refresh();

    System.out.println('\n');                       // tiparim un rand liber

//  ----------------- aici facem saltul intre cel 2 pagini web si le tiparim ca sa vedem cu iese -----------------------

        System.out.println("De aici incepem sa tiparim iar paginile si incercam sa ne plimbam intre ele");

//  facem captura de id's pentru browser-ul Windows
        Set<String> windowIDs = driver.getWindowHandles();  // inmagazinam 2 windows ID's
        Thread.sleep(500);

//  Definim lista /colectia de ID-uri - convertin setul la forma de list ca sa putem defini valorile, le schimbam in 2 liste
        List<String> windowidlist = new ArrayList<>(windowIDs);

//  Acum lista contine 2 valori, 0 este parinte si 1 este copil
        String parentwindowID = windowidlist.get(0);
        String childwindowID = windowidlist.get(0);

//  Tiparim cele 2 pagini ca sa le vizualizam in formatul lor tip cod :
        System.out.println(parentwindowID);
        System.out.println(driver.getCurrentUrl());

        System.out.println('\n');
        driver.navigate().forward();
        System.out.println(childwindowID);
        System.out.println(driver.getCurrentUrl());

        System.out.println('\n');
        driver.navigate().back();
        System.out.println(parentwindowID);
        System.out.println(driver.getCurrentUrl());


        Thread.sleep(5000);
        driver.quit();
    }

}
