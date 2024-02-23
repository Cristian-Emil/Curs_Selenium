import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

// Partea de Driver Manager a fost mutat in fisierul drivers/DriverManager de unde este apelata prin extinderea clasei

public class Locatoare_elemente extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  deschidem aplicatia - open app
//        driver.get("https://practice.automationtesting.in/test-cases/");
        driver.get(" https://www.emag.ro/");
        driver.manage().window().maximize();

//  Deschidem un click pe partea de HEAD a paginii ca sa stim sigur ca suntem pe ea
        driver.findElement(By.id("masthead")).click();

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
Aici incepe exercitiul pentru SELECTORI
    Dupa ce ne-am focalizat pe pagina de EMAG o sa folosim diversi selectori ca a identificam elementele cautate oe pagina
    Acestia sunt :
    By.Name
    By.className
    By.tagName
    By.linktext
    By.partial linktext
    By.id
    By.cssSelector
    By.xpath
    In clasele urmatoare o sa le explicam mai pe larg cum si in ce fel se utilizeaza.

Locatoarele de tip cssSelectro si xpath se numesc SPECIALE sau CUSTMIZED LOCATOR si au avantajul ca pe acestea putem sa
ne plimbam inainte si inapoi pe elementul cautat, nefiind nevoie sa-l specificam exact pe acela. El poate sa fie specificat
cu o X treapte mai sus sau mai jos fata de pozitia curenta.
Celelalte se numesc locatoare simple si ele trebuie sa identifice / defineasca exact elementul la care se refera

Pentru ca lucram ANONIM , partea de logare de mai jos o comentam
*/


////  intram in cont
//        driver.findElement(By.cssSelector("#my_account > i")).click();
////  inseram datele de contact
//        driver.findElement(By.id("user_login_email")).sendKeys("cristianzidarescu@hotmail.com");
//        driver.findElement(By.id("user_login_continue")).click();
//        driver.findElement(By.id("button-submit button")).click();

//  pt a inchide bara de Cookies dam intai un click pe ea si apoi accesam butonul de Acceptare toate:
        driver.findElement(By.cssSelector("body > div.gdpr-cookie-banner.js-gdpr-cookie-banner.pad-sep-xs.pad-hrz-none.show")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[11]/div/div[2]/button[3]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[12]/div/div/div[2]/form/div[2]/button[2]")).click();
        Thread.sleep(800);

//  inchidem banner-ul de intrare in cont apasand pe X
//        driver.findElement(By.xpath("/html/body/div[14]/div/button/i")).click();

//  cautam lista de echipamente de pe pagina de EMAG.RO
        driver.findElement(By.id("searchboxTrigger")).sendKeys("Telefon");
        Thread.sleep(800);
        driver.findElement(By.xpath("//*[@id=\"masthead\"]/div/div/div[2]/div/form/div[1]/div[2]/button[2]/i")).click();
        Thread.sleep(800);

//  alegem sa selectam produsele dupa pret crescator:
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[2]/div[1]/div[5]/div/div/div[2]/div[1]")).click();
        Thread.sleep(800);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[2]/div[1]/div[5]/div/div/div[2]/div[1]/div/ul/li[3]/a")).click();
        Thread.sleep(800);

/*  Alegem sa selectam produse cu pret cuprins intre 1000-1500 lei., Se observa ca optiunea este in afaraecranului.
  Pentru a rezolva aceatsa problema inchidem/restrangem restul optiunilor dand click pe acestea.
*/

        driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[1]/a")).click();
        Thread.sleep(800);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[2]/a")).click();
        Thread.sleep(800);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[3]/a")).click();
        Thread.sleep(800);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/section[1]/div/div[3]/div[1]/div[2]/div[2]/div[4]/a")).click();
        Thread.sleep(800);


//  dupa restrangerea campurilor si aparitia campului de selectie dupa pret, putem sa selectam campul produselor cu pret cuprins intre 1000-1500 lei:
        driver.findElement(By.xpath("//*[@id=\"js-filter-6411-collapse\"]/div[1]/a[6]")).click();
        Thread.sleep(800);

//  cautam toate imaginile cu numele "img" s generam o lista de elemente cu IMG
        List<WebElement> imagini= driver.findElements(By.tagName("img"));
        Thread.sleep(800);

//  ca sa determinam numarul total de elemente o sa le afisam. La lista folosim sintaxa ... numele.size()
        System.out.println("Numarul de element de tip imagine = " + imagini.size());

//  cautam toate link-urile cu numele "a" de la ancora si generam o lista de elemente
        List<WebElement> link_uri= driver.findElements(By.tagName("a"));
        Thread.sleep(800);

//  ca sa determinam numarul total de elemente o sa le afisam. La lista folosim sintaxa ... numele.size()
        System.out.println("Numarul de element de tip link = " + link_uri.size());

//  cautam elementele care au acelasi "div class" comun si se GENEREAZA O LISTA cu numele casute1
//        final String casute1 = "card-item card-standard js-product-data";
//        List<WebElement> tabel1= driver.findElements(By.cssSelector("div[class]"));

        List<WebElement> tabel1 = driver.findElements(By.cssSelector("div.filter.filter-default.js-filter"));
        Thread.sleep(800);

//  dupa determinarea numarul total de elemente le afisam. IN cazul listelor folosim sintaxa ... numele.size()
        System.out.println("Numarul de element de tip casuta = " + tabel1.size());

        Thread.sleep(1000);
//        driver.close();               // cand si cand primim mesaj de eroare de la inchiderea chrome-ului , il comentam
        driver.quit();
    }


 /* --------------------------------------------------------------------------------------------------------------------

Atentie:
Cand folosim sintaxele findElement si findElements avem urmatoarele rezultate:
- daca se potrivesc cu UN SINGUR ELEMENT o sa returneze:
    findElement     --->    un singur webelement si returneaza un singur                WebElement
    findElements    --->    un singur webelement si returneaza o lista cu un elemnt     List<WebElement>
ambele o sa returneze in acest scenariu UN SINGUR ELEMENT

- daca se potrivesc cu MAI MULTE ELEMENTE o sa returneze:
    findElement     --->    un singur webelement si returneaza PRIMUL element           WebElement
    findElements    --->    mai multe webelement si returneaza o lista de genul         List<WebElement>
observam ca se modifica modul in care se returneaza valorile

- daca locatorul este INCORECT o sa returneze o eroare sau valoare zero:
    findElement     --->    o sa returneze "NoSuchElementException"
    findElements    --->    o sa returneze ZERO pentru ca nu a gasit nici un element

 */

}
