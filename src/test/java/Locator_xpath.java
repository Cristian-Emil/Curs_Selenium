/*
Definirea si utilizarea locatorului de tip XPATH
 */
import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Locator_xpath extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  deschidem aplicatia - open app
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();

//  dam un click pe partea de header ca sa fim siguri ca suntem pe acesta pagina
        driver.findElement(By.className("header-logo")).click();

//  Ne concentram pe pagina deschisa , focalizare pe aceasta
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

/*
Aici incepe exercitiul pentru SELECTORII de tip xpath
    Dupa ce ne-am focalizat pe pagina de nopCommerce o sa folosim diversi selectori de tip cssSelector
    Acestia sunt :
    Absolut xpath - acesta afiseaza calea completa  ->  /html/body/div[6]/div[1]/div[2]/div[2]/form/input
    Relativ xpath - afiseaza calea relativa         ->  - //*[@id="small-searchterms"]

    Un "relativ xpath" are sintaxa:
    //tagname[@attibute='value']

    Formele de xpath cu mai multe atribute:
    xpath functions - text() normalized-pace() contains()start-with()
    xpath operators - and/or
    xpath axes      - following , preceding, sibling, self, ancestor, etc ....

Aceste locatoare de tip cssSelectro se numesc locatore speciale sau CUSTOMIZED LOCATORS
*/

        //  aici avem RELATIV xpath pentru ambele linii
        driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]")).sendKeys("phone");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"small-search-box-form\"]/button")).click();
        Thread.sleep(1000);


        //  folosim varianta ABSOLUT xpath
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[2]/form/input")).sendKeys("HTC");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[2]/form/button")).click();

        Thread.sleep(2500);
    driver.quit();

    }

/*
ATENTIE: Mereu se verifica care din variantele de selector este cea mai buna pentru o corecta identificare a elementului
    cautat

    DOM - Document Object Model ... este un concept fundamental in dezvoltarea web, el reprezentand o structură de arbore
    a unei pagini web. Cand browserukl afiseaza o pagină web, acesta creeaza o reprezentare interna a paginii sub formă
    de obiecte, care sunt organizate intr-o structura ierarhica numită DOM.
    Oricare obiect are o adresa - address of element object
 */
}
