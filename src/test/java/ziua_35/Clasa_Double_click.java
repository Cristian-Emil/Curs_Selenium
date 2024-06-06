package ziua_35;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_Double_click {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_ondblclick3");

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
    Avem 2 campuri, in campul1=Field1 avem un text carfe trebuie inserat in campul2=Filed2
    Aceasta actiune se face primn apasarea de doua ori la rand a butonului de "Copy Text"
    Pentru a captura elementul folosim prima data - switchTo()
    Apoi cautam cu driver.findElement elementul dorit pt captura
 */

        driver.switchTo().frame("iframeResult");  // aici intram in fram-ul pe care lucram

        WebElement field1 = driver.findElement(By.xpath("//*[@id='field1']"));
        field1.clear();                            // curatam campul1 ca sa inseram textul "Welcome"
        field1.sendKeys("Welcome");     // transmitem CHEIA de care avem nevoie in campul1
        Thread.sleep(1500);

//  ATENTIE aici in loc de ghilimele simple '...' o sa folosim ghilimele duble "..." deci inaintea lor pune \ .
        WebElement buton= driver.findElement(By.xpath("//button[normalize-space()='Copy Text']"));
//                                                                  "//*[@id=//button[normalize-space()=\"Copy Text\"]"
        Thread.sleep(1500);
        Actions actiune = new Actions(driver);
        actiune.doubleClick(buton).perform();             // aici se da dubluClick

        Thread.sleep(5000);
//  Se face verificarea si validarea ca in campul2 este inserast textul din primul camp.

        WebElement field2 = driver.findElement(By.xpath("//*[@id='field2']"));
        String copiedText = field2.getAttribute("value");

        System.out.println("Textul copiat este : " + copiedText);

        if(copiedText.equals("Welcome"))
        {
            System.out.println("Testul se verifica");
        }
        else
        {
            System.out.println("Testul a picat");
        }
/*
    Diferenta dintre - getText() si getAttribute()

    daca avem <input id='abc'> testing </ input> testing - inner text
    comanda findElement(Loc).getText() --- va returna --- testing
    comanda findElement(Loc).getAttribute() --- va returna --- nothing

    daca avem <input id='abc' value=testing / > testing - noinner text
    comanda findElement(Loc).getText() --- va returna --- nothing
    comanda findElement(Loc).getAttribute() --- va returna --- testing

    getText returneaza textul interior al elementului (inner text of element)

    getAttribute("value:) --- testing
    getAttribute("id") ------ abc

    deci getText va returna doar textul din interiorul elementului in timp ce getAttribute poate retruna textul sau
    valoarea din interiorul elementului, in functie de cum este adresat.
 */

    Thread.sleep(3500);
    driver.quit();

    }
}
