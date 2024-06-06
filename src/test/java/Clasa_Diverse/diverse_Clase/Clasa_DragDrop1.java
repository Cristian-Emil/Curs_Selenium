package Clasa_Diverse.diverse_Clase;
/*
Vedem cum se poate da un DRAG and DROP
Trebuie sa identificam sursa si destinatia - SOURCES & TARGET element

 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_DragDrop1 {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

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
//  Eliminam pagina de acceptare si dam un click pe ea in sectiounea "Accept all" .
//        driver.findElement(By.id("accept-choices")).click();
//        Thread.sleep(500);

//  Generam clasa action ca sa vedem de unde luam si unde punem:
        Actions actiune = new Actions(driver);

//  Sursa si destinatia si din findElement le transformam in WebElement - o sa facem pt 2 perechi de elemente:
        WebElement roma = driver.findElement(By.xpath("//*[@id=\"box6\"]"));
        WebElement italy = driver.findElement(By.xpath("//*[@id=\"box106\"]"));
        Thread.sleep(500);
        actiune.dragAndDrop(roma, italy).perform();     //  acaeasta este secventa de Drag and Drop

        WebElement washington = driver.findElement(By.xpath("//*[@id=\"box3\"]"));
        WebElement usa = driver.findElement(By.xpath("//*[@id=\"box103\"]"));
        Thread.sleep(500);
        actiune.dragAndDrop(washington, usa).perform();     //  acaeasta este secventa de Drag and Drop a doua

        WebElement madrid = driver.findElement(By.xpath("//*[@id=\"box7\"]"));
        WebElement spain = driver.findElement(By.xpath("//*[@id=\"box107\"]"));
        Thread.sleep(500);
        actiune.dragAndDrop(madrid, spain).perform();     //  acaeasta este secventa de Drag and Drop a treia


        System.out.println("Daca sistemul afiseaza acest text inseamna ca nu are eroare!");
    Thread.sleep(3000);
    driver.quit();

    }
}
