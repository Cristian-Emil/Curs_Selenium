package ziua_26;/*
Ce este conditional commands, cum apelam/accesam aceste comenzi prin WebElement
 */

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Clasa_WebDriver_CondCommand extends DriverManager {


    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
//  Tiparim pe ecran ID-ul paginii curente
//            System.out.println("ID-ul paginii curente : " + window);
        }

/*
    conditional commands - accesam aceste comenzi prin webElement, nu instanta insasi
    ele returneaza valori true/fals (valori de tip boolean)

    isDisplayed()   - este afisat  si afiseaza true daca elementul este afisat si fals daca nu apare pe ecran
    isEnabled()     - de obicei este activ sau nu . Se aplica la inbox, checkbox si returneaza true sau fols
    is Selected()   - se aplica la radiobutton, drop-box, checkbox si afiseaza true sau fals

 */
//  Cautam logo-ul de pe pagina pentru isDisplayed si isEnabled
        WebElement logo = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img"));
        System.out.println("Varinata lunga - Status logo : " + logo.isDisplayed());
        Thread.sleep(1000);

//  sau utilizam varianta scurta
        boolean status = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a/img")).isDisplayed();
        System.out.println("Varianta scurta - Status logo :" + status);
        Thread.sleep(1000);

//  verificam casuta de cautare - search box:
        WebElement searchbox = driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));
        System.out.println("Afisare Search Box : " + searchbox.isDisplayed());
        System.out.println("Status Search Box : " + searchbox.isEnabled());
        Thread.sleep(1000);

//  incercam sa ne inregistram pe pagina, deci apasam login si apoi activam casuta de Register cautand un RADIOBUTTON:
        WebElement logare = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));
        logare.click();
        Thread.sleep(1000);

        WebElement register = driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[1]/div[3]/button"));
        register.click();
        Thread.sleep(1000);

        WebElement male_dr = driver.findElement(By.xpath("//*[@id=\"gender-male\"]"));
        WebElement female_dr = driver.findElement(By.xpath("//*[@id=\"gender-female\"]"));

//  verificam statusul inainte de selectare:
        System.out.println("Inainte de selectare avem statusul : ");
        System.out.println("Status male : " + male_dr.isSelected());       // asteptam sa afiseze false
        System.out.println("Status female : " + female_dr.isSelected());     // asteptam sa afiseze false

//  verificam statusul dupa selectarea unui butaon - selectam butonul MALE :
        male_dr.click();
        System.out.println("Dupa selectare butonul de 'male' avem statusul : ");
        System.out.println("Status male : " + male_dr.isSelected());
        System.out.println("Status female : " + female_dr.isSelected());


        Thread.sleep(2500);
        driver.quit();
    }

}
