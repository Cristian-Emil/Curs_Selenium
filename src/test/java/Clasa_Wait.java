import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_Wait extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
//  Adaugam un IMPLICIT wait de 5 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
//  Pentru ca am pus acest implict wait o se comantem acele Thread.sleep care ne incetinesc testarea

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
//        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
//        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
/* WAIT COMMANDS

        implicit wait
        explicit wait / fluent state

        sleep(() : este generat/furnizat de java insusi (nu este o comanda Selenium)
Incepem sa generam codul pentru fucntiile implicit si explicit
*/

//        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
//        Thread.sleep(1000);

//        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
//        Thread.sleep(1000);

//        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        driver.findElement(By.className("oxd-button")).click();
        Thread.sleep(5000);

        driver.quit();

    }
/*
Avantaje si dezavantaje ca sa utilizam comanda SLEEP(timp in milisecunde) care este un EXPLICIT WAIT
    - este usor de folosit

    - daca timpul afista nu este suficient o sa arunce o exceptie
    - o sa astepte tot timpul afisat pana acesta se consuma. Poate duce la intarzierea testarii
    - este necesar sa fie specificat clar in fiecare punct unde avem nevoie

La acesta trebuie sa declaram si apoi sa o utilizam - Thread.sleep(time - milisecunde)


Avantaje si dezavantaje ca sa utilizam comanda IMPLICIT WAIT
    - se specifica o singura data la inceputul programului
    - nu o sa astepte tot timpul daca elementul devine vizibil
    - se valid pentru toate elementele inserate in testare

    - daca timpul afista nu este suficient o sa arunce o exceptie, din acest motiv o sa punem timp mare
    - daca timpul este prea mare o sa astepte pana acesta este consumat integral
    - nu mereu, cand netul are viteza mica asteapta pana pagina se incarca si arunca o exceptie (constatat pe retele lente)

 */

}
