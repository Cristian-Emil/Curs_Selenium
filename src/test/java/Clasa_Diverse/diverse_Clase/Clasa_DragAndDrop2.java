package Clasa_Diverse.diverse_Clase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_DragAndDrop2 {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("http://www.dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

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

        WebElement drag_box1 = driver.findElement(By.id("box1"));
        WebElement drop_box1 = driver.findElement(By.id("box101"));
        actiune.dragAndDrop(drag_box1, drop_box1).perform();

        WebElement drag_box2 = driver.findElement(By.id("box2"));
        WebElement drop_box2 = driver.findElement(By.id("box102"));
        actiune.dragAndDrop(drag_box2, drop_box2).perform();

        WebElement drag_box3 = driver.findElement(By.id("box3"));
        WebElement drop_box3 = driver.findElement(By.id("box103"));
        actiune.dragAndDrop(drag_box3, drop_box3).perform();

        WebElement drag_box4 = driver.findElement(By.id("box4"));
        WebElement drop_box4 = driver.findElement(By.id("box104"));
        actiune.dragAndDrop(drag_box4, drop_box4).perform();

        WebElement drag_box5 = driver.findElement(By.id("box5"));
        WebElement drop_box5 = driver.findElement(By.id("box105"));
        actiune.dragAndDrop(drag_box5, drop_box5).perform();

        WebElement drag_box6 = driver.findElement(By.id("box6"));
        WebElement drop_box6 = driver.findElement(By.id("box106"));
        actiune.dragAndDrop(drag_box6, drop_box6).perform();

        WebElement drag_box7 = driver.findElement(By.id("box7"));
        WebElement drop_box7 = driver.findElement(By.id("box107"));
        actiune.dragAndDrop(drag_box7, drop_box7).perform();


// Array-ul bidimensional folosind obiectele WebElement
        WebElement[][] dragAndDropPerechi = {
                {drag_box1, drop_box1},
                {drag_box2, drop_box2},
                {drag_box3, drop_box3},
                {drag_box4, drop_box4},
                {drag_box5, drop_box5},
                {drag_box6, drop_box6},
                {drag_box7, drop_box7},
        };

// Dupa efectuarea operatiilor de drag-and-drop verificam pozitia finala
        boolean allDropsSuccessful = true;

        for (WebElement[] pair : dragAndDropPerechi) {
            WebElement drop_Box = pair[0];
            Thread.sleep(500);
            WebElement drag_Box = pair[1];
            Thread.sleep(1000);             //  pauza pentru a permite DOM-ului sa se actualizeze


// Verificăm poziția finală a elementului drag
            int dragBoxX = drag_Box.getLocation().getX();
            int dragBoxY = drag_Box.getLocation().getY();
            int dropBoxX = drop_Box.getLocation().getX();
            int dropBoxY = drop_Box.getLocation().getY();
            int dropBoxWidth = drop_Box.getSize().getWidth();
            int dropBoxHeight = drop_Box.getSize().getHeight();

// Verificam dacă dragBox este acum copilul lui dropBox
            if (dragBoxX < dropBoxX || dragBoxX > (dropBoxX + dropBoxWidth) ||
                    dragBoxY < dropBoxY || dragBoxY > (dropBoxY + dropBoxHeight)) {
                allDropsSuccessful = false;
                System.out.println("Drag-and-drop esuat pentru drag-ul: " + drag_Box.getAttribute("id"));
            }
        }

        if (allDropsSuccessful) {
            System.out.println("Testul e OK");
        } else {
            System.out.println("Testul a eșuat");
        }

        Thread.sleep(5000);
        driver.quit();
    }
}


