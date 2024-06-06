package ziua_37;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.Set;

public class Clasa_InteractWithElementUsingJS {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
//        ChromeDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));

//---------------------------- alegem ecranul pe care sa afiseze pagina testata ----------------------------------------

// Obține lista de ecrane disponibile
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

// Alege ecranul dorit (de exemplu, ecranul 1 sau 2)
        int screenIndex = 1; // 0 pentru primul ecran, 1 pentru al doilea ecran, etc.

        if (screenIndex >= screens.length) {
            System.out.println("Ecranul specificat nu există.");
            return;
        }

        GraphicsDevice screen = screens[screenIndex];
        Rectangle bounds = screen.getDefaultConfiguration().getBounds();

//  Afișează fereastra browserului pe ecranul selectat
        driver.manage().window().setPosition(new org.openqa.selenium.Point(bounds.x, bounds.y));
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(bounds.width, bounds.height));

//----------------------------------------------------------------------------------------------------------------------

//  maxiumizam imaginea pe ecranul 2 :
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");

//------------------------------------------- partea de focalizare pe pagina -------------------------------------------
                String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//------------------------------------- aici incepem sa scriem programul -----------------------------------------------

//  trecem pe primul frame ca sa avem control
//        driver.switchTo().frame(0);

//  nu cream un OBIECT NOU pentru ca folosim direct JavaScript Executor unde JavaScript este o interfata
        JavascriptExecutor js = (JavascriptExecutor) driver;

/*
    daca utilizam :
        ChromeDriver driver = new ChromeDriver();

    putem sa apelam JS direct cu linia:
        JavascriptExecutor js= driver;          //  pt ca avem driverul definit direct.

    toate acestea sunt legate de ierarhia dintre clase. Aici o sa avem ca ChromeDriver este un copil al JS si o sa
    mosteneasca toate proprietatile acesteia.
*/

// ------------------- Pentru ca am vazut cum NU MERGE acel SIT-BLOG face o verificare pe  outlook ---------------------

//        driver.get("http://www.hotmail.com/");
//
//        WebElement Sign_in = driver.findElement(By.id("mectrl_headerPicture"));
//        Sign_in.click();
//        Thread.sleep(1500);
//
//        WebElement Email = driver.findElement(By.id("i0116"));
//        Email.sendKeys("cristianzidarescu@hotmail.com");
//        Thread.sleep(1500);
//
//        WebElement Next = driver.findElement(By.xpath("//*[@id=\"i0281\"]/div[4]/div/div/div/div"));
//        Next.click();
//        Thread.sleep(1500);

/*
    si constatam ca OUTLOOK.COM functioneaza, deci problema e de pe site pt ca elementul pare sa nu fie adresabil
    Adevarata problema este linia de comanda nr. 40 care nu functioneaza pt ca pagina a fost modficiata :
        driver.switchTo().frame(0);
    ea avand un singur FRAME si nou mai multe. Astfel toate liniile de mai jos se comenteaza ca nu isi au roul
*/
//  Avem varianta standard de gasesire a elementul pe pagina noastra:

//        WebElement nume = driver.findElement(By.xpath("//*[@id=\"name\"]"));
//        nume.sendKeys("Vasile a lu' Ion");

//  Gasim si identificam iframe-urile dupa care iterăm prin el pentru a găsi elementele dorite - daca exista
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nume = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"name\"]")));
        js.executeScript("arguments[0].setAttribute('value','Vasile al lui Ion')", nume );
        Thread.sleep(1000);

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]")));
        js.executeScript("arguments[0].setAttribute('value','cristian_teste@outlook.com')", email );
        Thread.sleep(1000);

        WebElement nr_tel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"phone\"]")));
        js.executeScript("arguments[0].setAttribute('value','+40721212121')", nr_tel );
        Thread.sleep(1000);

        WebElement adresa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"textarea\"]")));
        js.executeScript("arguments[0].setAttribute('value','RO IF str.Mosi No.7')", adresa );
        Thread.sleep(2000);

//  Apelam radio-button - atentie pagina modificat - pentru a selecta elementul GENDER
        WebElement buton_radio = driver.findElement(By.xpath("//*[@id=\"male\"]"));
//        buton_radio.click();              //  este varianta standard de dat click
        js.executeScript("arguments[0].click()", buton_radio);
        Thread.sleep(1000);

//  Apelam check-box - atentie pagina modificat - pentru a selecta elementul DAY
        WebElement ziua = driver.findElement(By.xpath("//*[@id=\"monday\"]"));
//        ziua.click();              //  este varianta standard de dat click
        js.executeScript("arguments[0].click()", ziua);
        Thread.sleep(1000);

        WebElement tara = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"country\"]")));
        js.executeScript("arguments[0].setAttribute('value','uk')", tara );
        Thread.sleep(1000);

        WebElement Submit_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"country\"]")));
//        Submit_button.click();              //  este varianta standard de dat click
        js.executeScript("arguments[0].click()", Submit_button);
        Thread.sleep(2500);

//  ATENTIE - arguments[0] - este ales mereu 0 pentru ca folosim un singur element. In cazul in care folosim mai multe schimbam indexul

    Thread.sleep(3500);
    driver.close();
    driver.quit();



/*
ATENTIE - diferenta dintre obiecte apelate si obiecte neapelate

Angajat - realizam pt el obiectul angajat1
Angajat - angajat1

daca se creeaza si angajat 2 cu conditita:
angajat2 = angajat1 - este valid pt ca angajat1 exista

Student - nu facem pentru el nci un obiect
daca incercam sa asignam :
Student stud = (Student)angajat1 - nhu functioneaza pt ca nu avem obiect la care sa ne adresam.


*/

    }
}
