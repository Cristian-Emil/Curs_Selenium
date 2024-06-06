package ziua_38;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class Clasa_CapturaEcran {

    public static void main(String[] args) throws InterruptedException, IOException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://demo.nopcommerce.com");

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
/*
    face o captura de ecran - INTEGRAL
    pentru ca e un driver adaugam (TakesScreenshot) inainte de driver
    daca mai sus era definit Chrome Driver in loc de WenDriver se putea adresa direct cu

    TakesScreenshot t_s = driver;

    pentru ca mostenea toate proprietaile driver-ului - MOSTENIRE / INHERITANCE
    Captura de ecran se face sub forma de fila deci scrie FILE si acesta o sa fie stocat in memorie
    O sa alegem o locatie unde sa punem acesta fila cu screeshot-ul
*/

        TakesScreenshot t_s = (TakesScreenshot) driver;
        File screen_full = t_s.getScreenshotAs(OutputType.FILE);
        File target_fila = new  File("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\screenshot_z31\\fullpage.png");

//  Copiem acel "screen_full" in fila "fullpage.png" din fisierul "screenshot_z31" prin intermediul comenzii de mai jos
//  Pt a copia folosim clasa File Utils - FILE COPYE
        FileUtils.copyFile(screen_full, target_fila);

//  In cazul in care dorim sa facem captura a unui element specific de pe pagina sau doar o parte din aceasta ----------
//  Pentru un ANUMIT ELEMENBT trebuie sa gasim WebElement-ul acestuia si sa-l capturam - -  LAPTOP-ul si FEATURED PRODUCTS

        WebElement feat_prod = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div/div[4]"));
        File fila1 = feat_prod.getScreenshotAs(OutputType.FILE);
        File captura1 = new  File("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\screenshot_z31\\feat_prod.png");
        FileUtils.copyFile(fila1, captura1);

        WebElement poza_laptop = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div/div[4]/div[2]/div[2]/div"));
        File fila2 = poza_laptop.getScreenshotAs(OutputType.FILE);
        File captura2 = new  File("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\screenshot_z31\\poza_laptop.png");
        FileUtils.copyFile(fila2, captura2);

//  Pentru verificare o sa facem un screenshot la logoul de pe pagina:\
        WebElement logo_pag = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[1]/a"));
        File fila3 = feat_prod.getScreenshotAs(OutputType.FILE);
        File captura3 = new  File("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\screenshot_z31\\logo_pag.png");
        FileUtils.copyFile(fila3, captura3);

//  ATENTIE: CITITI CU ATENTIE CE ESTE MAI SUS CA SA INTELEGETI DIFERENTA INTRE FULL SCRENSHOT SI PT UN ELEMENT ANUME

    Thread.sleep(3500);
    driver.close();
    driver.quit();
    }
}
