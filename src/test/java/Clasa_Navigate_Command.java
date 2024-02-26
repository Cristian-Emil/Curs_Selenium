/*
Comenzi de navigare pe paginile web si site
 */

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
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

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
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

//  facem refresh la pagina s-o reimprospatam
        driver.navigate().refresh();

        Thread.sleep(5000);

        driver.quit();
    }

}
