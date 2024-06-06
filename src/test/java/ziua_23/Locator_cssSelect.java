package ziua_23;/* Atentie : partea de Driver Manager a fost mutat in fisierul drivers/DriverManager pentru na usura cklasa si
acesta este  apelata de acolo prin extinderea clasei
*/

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Locator_cssSelect extends DriverManager {

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

        Thread.sleep(2000);
/*
Aici incepe exercitiul pentru SELECTORII de tip cssSelector
    Dupa ce ne-am focalizat pe pagina de nopCommerce o sa folosim diversi selectori de tip cssSelector
    Acestia sunt :
    tag & id                - utilizeaza # intre prima si a doua parte ..... input#denumire sau #denumire
    tag & class             - utilizeaza . intre prima si a doua parte ..... input.denumire
    tag & attribute         - utilizeaza [] in formarea elementului cautat ..... input[nume='denumire']
    tag & class atribute    - utilizeaza .[] in formarea elementului cautat ..... input.[nume='denumire']

Aceste locatoare de tip cssSelectro se numesc locatore speciale sau CUSTOMIZED LOCATORS
*/

    //  folosim varianta tag & class.
/*  ATENTIE: aici pentru ca avem clasa "search-box-text ui-autocomplete-input" , sistemul o sa recunoasca doar partea
    pana unde apare spatiu. Deci o sa scriem in sintaxa doar "search-box-text" iar restul se elimina ca sa nu primim
    eroare la citirea clasei
 */
        driver.findElement(By.cssSelector("input.search-box-text")).sendKeys("phone");
        Thread.sleep(1000);

        //  folosim varianta tag & id pt linia 57
        driver.findElement(By.cssSelector("#small-search-box-form > button")).click();
        Thread.sleep(1000);

        //  folosim varianta tag & attribute pt linia 61 si tag & id pentru linia 63
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("HTC");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#small-search-box-form > button")).click();

        //  folosim varianta tag & class attribute linia 66 si tag & id pentru linia 68
        driver.findElement(By.cssSelector("input.search-box-text[name='q']")).sendKeys("MacBook");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#small-search-box-form > button")).click();

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
