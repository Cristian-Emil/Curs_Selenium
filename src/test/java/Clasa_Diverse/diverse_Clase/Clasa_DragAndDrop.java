package Clasa_Diverse.diverse_Clase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_DragAndDrop {

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

        WebElement drop_box1 = driver.findElement(By.xpath("//div[@id='box1']"));
        WebElement drag_box1 = driver.findElement(By.xpath("//div[@id='box101']"));
        actiune.dragAndDrop(drop_box1, drag_box1).perform();

        WebElement drop_box2 = driver.findElement(By.xpath("//div[@id='box2']"));
        WebElement drag_box2 = driver.findElement(By.xpath("//div[@id='box102']"));
        actiune.dragAndDrop(drop_box2, drag_box2).perform();

        WebElement drop_box3 = driver.findElement(By.xpath("//div[@id='box3']"));
        WebElement drag_box3 = driver.findElement(By.xpath("//div[@id='box103']"));
        actiune.dragAndDrop(drop_box3, drag_box3).perform();

        WebElement drop_box4 = driver.findElement(By.xpath("//div[@id='box4']"));
        WebElement drag_box4 = driver.findElement(By.xpath("//div[@id='box104']"));
        actiune.dragAndDrop(drop_box4, drag_box4).perform();

        WebElement drop_box5 = driver.findElement(By.xpath("//div[@id='box5']"));
        WebElement drag_box5 = driver.findElement(By.xpath("//div[@id='box105']"));
        actiune.dragAndDrop(drop_box5, drag_box5).perform();

        WebElement drop_box6 = driver.findElement(By.xpath("//div[@id='box6']"));
        WebElement drag_box6 = driver.findElement(By.xpath("//div[@id='box106']"));
        actiune.dragAndDrop(drop_box6, drag_box6).perform();

        WebElement drop_box7 = driver.findElement(By.xpath("//div[@id='box7']"));
        WebElement drag_box7 = driver.findElement(By.xpath("//div[@id='box107']"));
        actiune.dragAndDrop(drop_box7, drag_box7).perform();

        System.out.println("Prima parte a testului a fost efectuata - testul e OK");

        Thread.sleep(1500);

//  Se face verificarea prin care casutele au fost atasate corect. Se folosete optiunea FOR.

/*
    Testul de mai jos o sa dea FAIL pentru ca definirea elementelor dragAndDrop nu reusesc sa faca diferenta intre
    casuta inserata si casuta in care este inserata.
 */
        String[][] dragAndDropPerechi = {
                {"box1", "box101"},
                {"box2", "box102"},
                {"box3", "box103"},
                {"box4", "box104"},
                {"box5", "box105"},
                {"box6", "box106"},
                {"box7", "box107"}
        };

        Thread.sleep(1500);

        boolean allDropsSuccessful = true;

        for (String[] pair : dragAndDropPerechi) {
            WebElement drop_Box = driver.findElement(By.id(pair[0]));
            WebElement drag_Box = driver.findElement(By.id(pair[1]));
//            actiune.dragAndDrop(drag_Box, drop_Box).perform();            // am eliminat repetarea operatiei de dragAndDrop
            Thread.sleep(500); // Mică pauză pentru a permite DOM-ului să se actualizeze

// Verificăm poziția finală a elementului drag
            int dragBoxX = drag_Box.getLocation().getX();
            int dragBoxY = drag_Box.getLocation().getY();
            int dropBoxX = drop_Box.getLocation().getX();
            int dropBoxY = drop_Box.getLocation().getY();

// Verificăm dacă dragBox este în interiorul dropBox (simplificat)
            if (dragBoxX < dropBoxX || dragBoxX > (dropBoxX + drop_Box.getSize().getWidth()) ||
                    dragBoxY < dropBoxY || dragBoxY > (dropBoxY + drop_Box.getSize().getHeight())) {
                allDropsSuccessful = false;
                System.out.println("Drag-and-drop failed for: " + pair[1]);
            }
        }
        if (allDropsSuccessful)
        {
            System.out.println("Testul e OK");
        }
        else
        {
            System.out.println("Testul a eșuat");
        }

    Thread.sleep(5000);
    driver.quit();
    }
}
