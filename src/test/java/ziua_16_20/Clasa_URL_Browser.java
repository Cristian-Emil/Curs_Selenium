package ziua_16_20;/*
Utilizarea findElement pentru utilizarea unui anumit element cautat. Mai jos este prezenata modul in care il cautam in DOM.
Cautam in DOM - document object model, in linia din document elementul care defineste acesta casuta in HTML si apoi
sa cauta in lina de comanda locatia acestuia cu ID, cssSelector, XPATH

 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
//import java.util.NoSuchElementException;


public class Clasa_URL_Browser {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/CRISTIAN ZIDARESCU/IdeaProjects/- driver Selenium-Maven/ChromeDriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

//  Apelam URL-ul de care avem nevoie folosinf functia GET. URL-ul nsotru este "https://opensource-demo.orangehrmlive.com/"
        driver.get("https://opensource-demo.orangehrmlive.com/");

//  Maximizam pagina cat tot ecranul pt o vizibilitate buna si folosim linia de comanda:
        driver.manage().window().maximize();

        Thread.sleep(7500);

/*  Trebuie sa furnizam USERNAME - aici Admin si parola  - aici admin123
    In pagina sursa se da click dreapta, se apeleaza linia INSPECTION
    Cautam in DOM - document object model, in linia din document elementul care defineste acesta casuta in HTML si apoi
    sa cauta in lina de comanda locatia acestuia cu ID, cssSelector, XPATH

    Identificam elementul care trebuie apelat si il definim cu functia findElement
    Putem utiliza cssSelector sau Name - o sa prezentam ambele variante
    Pentru a scrie username-ul o sa folosim functia sendKeys si intre paranteze specificam numele
*/

//        WebElement userName = driver.findElement(By.cssSelector("#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div:nth-child(2) > div > div:nth-child(2) > input"));
        WebElement userName = driver.findElement(By.name("username"));
        userName.sendKeys("Admin");

        WebElement pass = driver.findElement(By.name("password"));
        pass.sendKeys("admin123");


//  Se putea face acest lucru utilizand un singur pas pentru fiecare linie utilizand comenzile urmatoare:
//      driver.findElement(By.name("username")).sendKeys("Admin");
//      driver.findElement(By.name("password")).sendKeys("admin123");

//  Pentru a da click pe butonul de login o sa folosim varinta scurta a liniei de comanda:
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        Thread.sleep(5000);

// Verificam un element de pe pagina respectiva inainte s-o inchidem
// Se va verifica daca pe pagina apare un banner - Dashboard


//  Varianta 1 - cea lunga in care cautam IMAGINEA
        WebElement imagine1= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6"));

        String elementul_cautat = imagine1.getAttribute("class");
        String elementul_gasit = "oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module";

//  Se verificam conditia folosind o functie conditionala, aceasta este IF care compara ce cautam cu ce exista:

        if(elementul_cautat.contains(elementul_gasit)){
            System.out.println("Testul 1 a trecut");
        }
        else {
            System.out.println("Nu a fost gasit elementul 1 cautat");
        }


//  Nu folosim in comanda If functia equals pentru ca cele 2 pot sa nu fie identice. Atunci utilizam CONTAINS - sa contina


//  Varianta 2 - cea scurta in care cautam TEXT-ul si folosim functia equals
        String eticheta_cautata = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6")).getText();
        String eticheta_gasita = "Dashboard";

        if(eticheta_cautata.equals(eticheta_gasita)){
            System.out.println("Testul 2 a trecut");
        }
        else {
            System.out.println("Nu a fost gasit elementul 2 cautat");
        }


//  Varianta 3 - in care utilizam functia "try-catch" incercand sa gasim o exceptie - folosim eticheta "Time at Work"
        String titlu1_cautat = null;
        try {
        titlu1_cautat = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/p")).getText();
        }
        catch(NoSuchElementException e) {
            titlu1_cautat = "";
        }

        String titlu1_gasit = "Time at Work";

        if(titlu1_cautat.equals(titlu1_gasit)){
            System.out.println("Testul 3 a trecut");
        }
        else {
            System.out.println("Nu a fost gasit elementul 3 cautat");
        }

//  La importarea NoSuchElementException trebuie sa fim atenti ca importul sa fie facut din Selenium si nu din Java - vezi liniile 5 si 6

//  punem un timp de asteptare ca sa vizualizam rezultatul:
        Thread.sleep(2500);


//  Si inchidem browserul cu "close" dupa care parasim pagina cu "quit" ca sa descarcam memoria computerului
//        driver.close();
        driver.quit();
// pentru ca primim mereu un mesaj de internal error am decis sa comentam linia de "close" si sa inchidem cu "quit"
    }

}
