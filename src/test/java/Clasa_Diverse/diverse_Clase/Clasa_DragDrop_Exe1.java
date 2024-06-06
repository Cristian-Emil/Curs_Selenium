package Clasa_Diverse.diverse_Clase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_DragDrop_Exe1 {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("http://demo.guru99.com/test/drag_drop.html");


//------------------------------------------- partea de focalizare pe pagina -------------------------------------------
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//  ----------------------------------- aici incepem sa scriem programul -----------------------------------------------
//  Ne mutam pe partea de pagina sa putem sa inchidem coockie-ul - Accept All
        driver.switchTo().frame("gdpr-consent-notice");
//        driver.findElement(By.id("gdpr-consent-notice"));
        Thread.sleep(500);

        WebElement buttonAccept = driver.findElement(By.cssSelector("#save > span.mat-button-wrapper > div > span"));
        buttonAccept.click();
        Thread.sleep(500);

//  Elementul pe care-l tragem.
        WebElement From=driver.findElement(By.xpath("//*[@id='credit2']/a"));
        Thread.sleep(500);
//  Elementul unde trabuie sa-i dam drumul.
        WebElement To=driver.findElement(By.xpath("//*[@id='bank']/li"));
        Thread.sleep(500);
//  Utilizam clasa Action pentru drag and drop.
        Actions actiune = new Actions(driver);
        Thread.sleep(500);
//  Tragem si dam drumul in noul camp de tinta
        actiune.dragAndDrop(From, To).build().perform();

        Thread.sleep(3000);
        driver.quit();
    }

/*
//  Aici aveam a doua varianta de apelare dar care nu o s-o utilizam acum

        System.setProperty("webdriver.chrome.driver"," E://Selenium//Selenium_Jars//chromedriver.exe ");
        driver= new ChromeDriver();
        driver.get("http://demo.guru99.com/test/drag_drop.html");
 */


/*  Mai jos avem o alta abordare pentru a inchide pagina de Manage Your Privacy:
//  Ne ducem pe pagina de cookie si apoi dam click pe butonul de acceptare
        try {
            Thread.sleep(5000); // Așteptați 5 secunde pentru încărcarea completă a paginii (pentru scopuri demonstrative)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(500);
        WebElement manageYourPrivacy = driver.findElement(By.id("gdpr-consent-tool-wrapper"));
        manageYourPrivacy.click();
        Thread.sleep(500);
*/

/*  O alta varianta si metoda de abordare aste sa generam o clasa:
ziua_la_gramada.Clasa_coockie
Pentru a accesa frame-ul de Manage Your Privacy dar nu-l utilizam acum

        ziua_la_gramada.Clasa_coockie.getClickAcceptAll().click();
        Thread.sleep(500);
        WebElement acceptare = driver.findElement(By.xpath("//*[@id=\"save\"]/span[1]/div/span"));
        acceptare.click();

Dar secventa trebuie extinsa si completata cu restul functiilor
*/


}
