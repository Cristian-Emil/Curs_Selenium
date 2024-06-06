package ziua_36;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_SliderElement {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://www.jqueryscript.net/demo/Price-Range-Slider-jQuery-UI/");

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

        Actions actiune = new Actions(driver);

        WebElement cursor_stg = driver.findElement(By.xpath("//*[@id='slider-range']/span[1]"));
        WebElement cursor_drpt = driver.findElement(By.xpath("//*[@id='slider-range']/span[2]"));

// trebuie sa gasim locatia acestor elemente cel minim si cel maxim :

        System.out.println("Locul cursorului stanga : " + cursor_stg.getLocation());
        System.out.println("Locul cursorului dreapta : " + cursor_drpt.getLocation());

/*
    ACESTEA NE VOR FURNIZA POZITIILE CELOR 2 CURSOARE - ca pereche de valori (x,y).
    CURSORUL STANGA se poate muta doar spre dreapta si valorile lui pe X se modifica de la mic la mare
    CURSORUL DREAPTA se poate muta doar spre stanga si valorile lui pe X se modifica de la mare la mic

    Pentru pagina accesta o sa ne afiseze valorile minima si maxima pt cele 2 cursoare:
    Locul cursorului stanga : (59, 250)
    Locul cursorului dreapta : (612, 250)
    unde valoarea lui Y=250 o sa fie mereu constanta

    O sa mutam cursorul stg & drpt folosind dragAndDrop si comanda PERFORM
    Pentru mutare la stanga definim valori pozitive, pentru mutare la dreapta definim valori negative
*/

        actiune.dragAndDropBy(cursor_stg, 100, 250).perform();  // unde avem x_min = 65
        actiune.dragAndDropBy(cursor_drpt, -125, 250).perform();  // unde avem x_max = 612

        System.out.println("Noua locatie a cursorului stanga : " + cursor_stg.getLocation());
        System.out.println("Noua locatie a cursorului dreapta : " + cursor_drpt.getLocation());

/*  dragAndDrop si slider sunt elemente ale Java , daca dorim sa facem scoll este nevoie sa apelam la JavaScript.
    SLIDER este parte a aplicatie , SCROLL nu este parte a aplicatie si deci nu se poate apela ca WebElement normal
    In cazul in care dorim sa face slide pe verticala trebui ca aplicatia sa permita acest lucru


    ATENTIE: trebuie sa maximizam pagina ca elementele sa poata sa fie gasite pe pagina. Daca pagina nu este maximizata la
    100% e posibil din cauza locatorului sa nu gaseasca elementul respectiv din cauza diferentei de ZOOM.
*/



    Thread.sleep(5000);
    driver.quit();
    }
}
