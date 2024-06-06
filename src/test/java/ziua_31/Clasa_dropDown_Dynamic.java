package ziua_31;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_dropDown_Dynamic extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://www.google.com");
        Thread.sleep(1000);


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//  Dupa intrarea pe pagina de Google inseram
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#SIvCob > a:nth-child(1)")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).sendKeys("selenium program");
        Thread.sleep(1000);
        List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class,'wM6W7d')]//span"));
        Thread.sleep(500);
        System.out.println("Numarul de sugestii : " + list.size());
        Thread.sleep(1000);

        for(int i=0; i<list.size(); i++)
        {
            System.out.println("Valorile sunt : " + list.get(i).getText());
            System.out.println(" ");
            String text = list.get(i).getText();
            if(text.equals("selenium programming"))
            {
                list.get(i).click();
                break;
            }
        }
        Thread.sleep(1000);

// Dupa verificarile realizate deschidem 2 ferestre noi in care apelam paginile de care avem nevoie
//  Apelam prima pagina, deschide o fereastră pentru a a apela si ne concentram pe ea
        ((JavascriptExecutor) driver).executeScript("window.open()");
        windowHandles = driver.getWindowHandles();
        String secondWindowHandle = "";
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindow) && !windowHandle.equals(driver.getWindowHandle())) {
                secondWindowHandle = windowHandle;
                break;
            }
        }

// Schimbam vederea pe noua fereastra
        driver.switchTo().window(secondWindowHandle);

// Navigam la prima pagina
        driver.get("https://www.selenium.dev/");
        Thread.sleep(3000);


//  Apelam a doua pagina, deschide o nouă fereastrq pentru a doua apelare
        ((JavascriptExecutor) driver).executeScript("window.open()");
        windowHandles = driver.getWindowHandles();
        String thirdWindowHandle = "";
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindow) && !windowHandle.equals(driver.getWindowHandle()) && !windowHandle.equals(secondWindowHandle)) {
                thirdWindowHandle = windowHandle;
                break;
            }
        }

// Schimbam vederea pe noua fereastra
        driver.switchTo().window(thirdWindowHandle);

// Navigam catre a doua pagina
        driver.get("https://www.browserstack.com/selenium");
        Thread.sleep(3000);

//  Inchidem toate ferestrele:
        driver.close();
        driver.quit();

    }
}
