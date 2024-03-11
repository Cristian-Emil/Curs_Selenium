package ziua_26;/*
Mai jos avem metoda de a face switch intre N ferestre

   getWindoweHandles() - returneaza mai multe browsere dinWindows intre care ne putem muta si focaliza

driver.switchTo().window(window id)

daca avem mai multe ferestre deschise
    driver.close() - va inchide doar pagina pe care suntem focalizati
    driver.quit()   - o sa incvhida toate ferestrele.

In cazul cand dorim sa inchidem o anumita fereastra sau mai multe avem comentarii de la linia
------------------ inchiderea unui anumit browser sau browsere in functie de cerinta -------------------------------


Deci utilizam 3 tipuri de switch-uri
1.  driver.switchTo().alert()
2.  driver.switchTo().frame()
3.  driver.switchTo().window()

*/

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_HandleBrowser_Method2 extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();


//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        driver.get("https://livedemo.installatron.com/1708670477orangehrm/web/index.php/auth/login");
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//  facem click pe linkul OrangeHrm.Inc ca sa deschidem o noua fereastra - doar ca acesta este in interiorul unui iframe:
        WebElement link = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[3]/div[2]/p[2]/a"));
        link.click();
        Thread.sleep(500);

//  facem captura de id's pentru browser-ul Windows
        Set<String> windowIDs = driver.getWindowHandles();  // inmagazinam N windows ID's
        Thread.sleep(500);


//  pentru a accesa mai multe ferestre folosim varianta cu FOR  - in bucla IF avem doua verificari ca suntem pe pagina CHILD:
//  Cu ea verificam titlul paginii ca sa fim siguri ca suntem pe pagina cautata

        for(String wind_id:windowIDs)
        {
            String titlu = driver.switchTo().window(wind_id).getTitle();
            if(titlu.equals("OrangeHRM HR Software | OrangeHRM"))
            {
//                driver.findElement(By.xpath("/html/body/nav/div/a/img")).click();
                driver.findElement(By.xpath("//*[@id=\"Form_submitForm_action_request\"]")).click();
            }
        }
//  Bucla de mai sus a fost gandita ca sa lucreze in special cu al doilea browser

// ------------------- inchiderea unui anumit browser sau browsere in functie de cerinta -------------------------------

//  Daca dorim sa inchidem browserele unul cate unul apelam la functia:

        for(String wind_id:windowIDs)
        {
            String titlu = driver.switchTo().window(wind_id).getTitle();
            if(titlu.equals("OrangeHRM HR Software || OrangeHRM"))
            {
                driver.close();
            }
        }
/*  si aceasta o sa inchida toate browserele care contin conditionala din if - TITLUL SPECIFICAT
        daca specific doar "OrangeHRM HR Software" o sa inchida acel browser
        daca specific doar "OrangeHRM " o sa inchida acel browser
cum in conditionala avem mai multe cerinte o sa fie inchise mai multe browsere.
*/

        System.out.println("Daca afiseaza acest mesaj inseamna ca programul a rulat complet si a ajuns la final");
    Thread.sleep(5000);
    driver.quit();

    }
}
