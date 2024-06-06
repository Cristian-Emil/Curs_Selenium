package ziua_28;/*
Cum gestionam trecerea dintr-un browser in altul
- deschidem primul browser
- accesam un link pentru un salt in alt browser
- deschidem alt browser dar noi suntem focalizati in primul browser, deci in al doilea nu se pot face actiuni.
- ne mutam in al doilea browser cu comanda:

    getWindoweHandle()  - returneaza un singur browser din Windows (cel in care suntem focalizati)
    getWindoweHandles() - returneaza mai multe browsere dinWindows intre care ne putem muta si focaliza

cu aceasta ne mutam intre ferestre
    driver.switchTo().window(window)

cu aceasta ne focalizam pe un browser anume - un anumit id
    driver.switchTo().window(window id)

toate acestea se vad in liniile 49-54

Mai jos avem metoda de a face switch intre 2 ferestre -PARINTE SI COPIL
 */


import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Clasa_HandleBrowser_Method1 extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();


//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");     // pagina inactiva
        driver.get("https://livedemo.installatron.com/1708670477orangehrm/web/index.php/auth/login");
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
        Set<String> windowIDs = driver.getWindowHandles();  // inmagazinam 2 windows ID's
        Thread.sleep(500);

//  Definim lista /colectia de ID-uri - convertin setul la forma de list ca sa putem defini valorile, le schimbam in 2 liste
        List<String> windowidlist = new ArrayList<>(windowIDs);

//  Acum lista contine 2 valori, 0 este parinte si 1 este copil
        String parentwindowID = windowidlist.get(0);
        String childwindowID = windowidlist.get(1);
        Thread.sleep(500);

//  Tiaprim cele 2 ID-uri ca sa le vizualizam
        System.out.println(parentwindowID);

//  Ca sa tiparim ID-ul copilului trebuie sa ne focalizam pe pagina respectiva
        System.out.println(childwindowID);
        Thread.sleep(500);

        driver.switchTo().window(childwindowID);
//  Pe noua pagina deschisa facem click pe "Contact Sales" dar dam un click pe pagina curenta sa nu fure click-ul:
//        driver.findElement(By.xpath("/html/body/div[3]/div/div/section[1]/div[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"Form_submitForm_action_request\"]")).click();
        Thread.sleep(500);

//  Si ne mutam inapoi pe pagina parinte cu comanda:
        driver.switchTo().window(parentwindowID);

//  transmitem un user name - admin - si parola - admin123 - pentru casutele corespunzatoare
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("admin123");

        //  Verificam existenta siglei HRM cu comanda TRY - CATCH dupa care inchidem fereastra
        try {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[1]/img"));

            // daca elementul este gasit, afisam un mesaj de prezenta
            System.out.println("Elementul exista pe pagina!");

            } catch (org.openqa.selenium.NoSuchElementException e) {
            // daca elementul nu este gasit, afisam mesaj de inexistenta
            System.out.println("Elementul nu exista pe pagina!");
        }

//  Asteptam cele 5 secunde si apoi inchidem toate ferestrele
        System.out.println("Daca afiseaza acest mesaj inseamna ca programul a ajuns la final");
    Thread.sleep(5000);
    driver.quit();
    }
}
