package ziua_37;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_ScrollingPage {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

//        driver.get("https://countries-ofthe-world.com/flags-of-theworld.html");

//  apare o fereastra de verificare - CLOUDFLARE - pe pagina de dam clic eticheta intai ca sa accesa poagina si apoi pe casuta ei.

//        driver.findElement(By.xpath("/html/body/div[1]/div/h1")).click();
//        Thread.sleep(5000);
//        WebElement cloudflare = driver.findElement(By.xpath("//*[@id=\"challenge-stage\"]/div/label/span[2]"));
//        Thread.sleep(2000);
//        cloudflare.click();

/*
    avem variante pentru casuta de CLOUDFLARE
        //*[@id="challenge-stage"]/div/label/span[2]
        //*[@id="challenge-stage"]/div/label/span[1]
        //*[@id="challenge-stage"]/div/label/input
*/
// pentru ca pagina respectiva a fost modificata si multe elemente au disparut o sa folosim URL-ul de mai jos

        driver.get("https://www.countryflags.com/");

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

//  dupa intrarea pe pagina facem scroll-down ca sa mergem in jos pe pagina, pt asta definim JS executor:
        JavascriptExecutor js = driver;                 // folosim varianta simplificat ca nu am definit WebDriver
        js.executeScript("window.scrollBy(0,2500)", "");
        System.out.println(js.executeScript("return window.pageYOffset;"));
        Thread.sleep(3000);

//  vrem sa facem scoll pana cand steagul Romania ajunge in partea de sus a paginii afisate:
        WebElement Romania = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[61]/a/img"));
        js.executeScript("arguments[0].scrollIntoView()", Romania);
        System.out.println(js.executeScript("return window.pageYOffset;"));
//  aflam si afisam cat am coborat pana sa vedem steagul Romaniei
        Thread.sleep(3000);

//  facem scoll pana ajungem in josul paginii si aceasta este afisata:
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        Thread.sleep(3000);

//  si dupa toate astea ne intoarcem la pozitia initial a paginii, pt asta scrie textul de emai sus cu "-" :
        js.executeScript("window.scrollBy(0,- document.body.scrollHeight)", "");

    Thread.sleep(5000);
    driver.close();
    driver.quit();
    }
}
