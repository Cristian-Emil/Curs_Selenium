package ziua_35;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_Actions_vs_Action {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

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

//  aceasta este actiunea de RIGHT-CLICK
        WebElement butonul = driver.findElement(By.xpath("/html/body/div/section/div/div/div/p/span"));
        Thread.sleep(500);
        Actions actiune = new Actions(driver);

//  avem varianta1 unde utilizam PERFORM()
        actiune.contextClick(butonul).perform();

//  avem varianta2 unde utilizam BUILD().PERFORM()
        actiune.contextClick(butonul).build().perform();

/*
    Deci diferenta dintre cele doua este ca :
    perform() - face direct actiunea care o fost generata inainte
    build().perform() - generaza o actiune si apoi o face

    deci daca avem doar - build() - atunci este creata o actiune dar nu este apelata/executata, nu apare rezultatul ei
    Acest lucru se paote face prin crearea unui de actiuni multiple dar acestea nu se executa la momentul cand au fost create
    Ulterior aceste actiuni create pot sa fie apealte in program cand avem nevoie.
    Astfel putem sa definim/generam mai multe actiuni care sa fie executate separat/independent la momentul la care avem
    nevoie de ele.

    Vezi modelul de mai jos :
 */

        Action actiuni_multiple = actiune.contextClick(butonul).build();       // aici am generat actiunile pt buton
//  aici inseram secventa de cod de care avem nevoie mai tarziu
        actiuni_multiple.perform();                                     // aici am executat actiunile cu buton X

/*
    Deci diferenta este cum generam si cu executam comenzile din program
    In varianta de build().perform() sau perform()-direct este exact ca atunci cand comandam piese pentru masina si dorim
    sa le instalam.
    perform() - am comandat toate piesele si ne apucam sa le montam pe toate in acelasi timp
    build().perform() - am comandat piesele si ne apucam sa le montam pe rand, la momentul in care avem nevoie apelam si
    facem actiunea care trebuie. Deci la momente diferite de timp
 */


    Thread.sleep(5000);
    driver.quit();

    }
}
