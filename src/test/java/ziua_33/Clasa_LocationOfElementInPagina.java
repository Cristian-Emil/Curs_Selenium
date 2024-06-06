package ziua_33;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_LocationOfElementInPagina {
/*
Pentru a localiza elementele avem nevoie de LOCATION-ul acestuia
 */
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
//  am mutat de aici linia de maximizare ecran , vezi liniile de mai jos

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

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

        WebElement logo_page = driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[1]/img"));
        Thread.sleep(1500);

        System.out.println("Nu maximizare si avem coordonatele : " + logo_page.getLocation());
//      Pentru valoare default avem coordonatele : (276, 50)
        Thread.sleep(1500);

        driver.manage().window().maximize();
        System.out.println("Maximizam pagina si avem coordonatele : " + logo_page.getLocation());
//      Maximizam si primim coordonatele (474,57)
        Thread.sleep(1500);

        driver.manage().window().minimize();
        System.out.println("Minimizam pagina si avem coordonatele : " + logo_page.getLocation());
//      Minimizam si primim coordonatele (277,45)
        Thread.sleep(1500);

        driver.manage().window().fullscreen();
        System.out.println("Minimizam pagina si avem coordonatele : " + logo_page.getLocation());
//      Facem full screen si primim coordonatele (476,121)
        Thread.sleep(1500);

//  Se observa ca in functie de ce varianta alegem avem valori diferite. Pt asta se alege MAXIMIZAREA ecranului.

//  In anumte cazuri putem sa setam limitele pe care dorim sa le utilizam

        Point punct= new Point(50,50);
        driver.manage().window().setPosition(punct);

        System.out.println("Dupa setare 'punct(50 x 50 )' avem coordonatele : " + logo_page.getLocation());
//      Facem punctul seata al ecranului primim coordonatele (277, 45)


    Thread.sleep(3500);
    driver.quit();

/*
    De verificat pagina :
    https://demo.guru99.com/test/drag_drop.html
    pentru dragAndDrop action
 */


    }
}
