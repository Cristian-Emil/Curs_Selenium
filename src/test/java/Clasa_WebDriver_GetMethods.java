//  Numele corect este de Get Methoids dar am preferat sa pastram numele initial.


/*
Utilizare comenzilor si metodelor in SELENIUM

    get methods
    conditional methods
    navigational methods
    browser methoids
    wait methods
 */

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Clasa_WebDriver_GetMethods extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();

/*  getWindiwsHandle este comanda cu care ne focalizam atentia pe pagina curenta.
    daca tiparim aceasta comanda o sa vedem ca browserul are mereu un alt ID. Deci acesta este dinamic.

    daca deschidem o pagina noua o sa observam ca driverul o sa ramana focalizat pe prima pagina - webpage deschis initial
    pentru a ne putea focalizarea atentia pe pagina curenta se foloseste comanda getWindowHandles

    getWindowHandle - focalizarea pe o pagina de browser deschisa
    getWindowHandles    - ne putem muta atentia pe mai multe pagini de browser deshise

    ID-ul este generat de browser, deci nu o sa fie retinut in memorie pt ca este dinamic si se modifica in mod curent.

*/

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
            System.out.println("ID-ul paginii curente : " + window);
        }
/*
get methods - putem utiliza aceste metode prin instanta webdriver apelata

    get(url)            `- returneaza un paremetru care este un STRING si e URL-ul specificat intre ghilimele
    getTitle()
    getCurrentURL()
    getPageSource()     - returneaza
    getWindowsHandle()  - returneaza browserul de Windows, unul anume pe care il apelam din functie
    getwindowHandles()  - returneaza toate browserele care sunt active
 */



        System.out.println("Titlul paginii apelate : " + driver.getTitle());

        System.out.println("URL-ul curent este :" + driver.getCurrentUrl());

//        System.out.println("Pagina sursa : " + driver.getPageSource());    //  pt ca afiseaza toata pagina il comenatam

//  Afisam doar elementele de tip html - scriem elementele in variabila TEXT si o afisam :
        String text= driver.getPageSource();
        System.out.println("Pagina sursa cu elemente HTML : " + text.contains("html"));

    Thread.sleep(2500);
    driver.quit();

    }

}
