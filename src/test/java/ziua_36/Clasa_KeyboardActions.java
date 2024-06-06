package ziua_36;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_KeyboardActions {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("http://text-compare.com");

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
//  inseram textul in campul 1
        driver.findElement(By.xpath("//textarea[@id='inputText1']")).sendKeys("Bine ai venit la cursul de AUTOMATION");
        Thread.sleep(1500);

// succesiunea de comenzi este control+a ; control+c , tab (pt trecerea din casuta 1i in casuta 2 si control+v
//  Pentru a realiza aceste actiuni apelam ACCESS CLASS

        Actions actiune = new Actions(driver);

//  control + a - selecvtam textul tot
        actiune.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        Thread.sleep(1500);

//  control + c - copiem textul selectat
        actiune.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Thread.sleep(2500);

//  TAB - ne mutam in casuta urmatoare
//        actiune.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
        actiune.sendKeys(Keys.TAB).perform();                      // putem sa utilizam varianta foarte scurta
        //  pentru ca e o singura tasta se pune direct keyDown si keyUp fara sa precizam cheia
        Thread.sleep(2500);

//  control + v - inseram textul copiat anterior
        actiune.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        Thread.sleep(2500);

/*
    Pentru toate tastele de pe tastatura care au multipla actiune si se folosesc combinate cu TASTA CONTROL, atunci se
    foloseste keyDown si keyUp si tasta specifica, dar pentru cheile individuala nu se specifica cheia cu comanda -
    sendKeys("...,") ci doar cu comanda simpla. Pentru urmatoarele chei cu actiune individuala se poate utiliza varianta
    descrisa mai jos :

    TAB             actiune.sendKeys(keys.TAB).perform()
    ENTER           actiune.sendKeys(keys.ENTER).perform()
    ESC             actiune.sendKeys(keys.ESC).perform()
    BAKSPACE        actiune.sendKeys(keys.BAKSPACE).perform()
    DELETE          actiune.sendKeys(keys.DELETE).perform()
    INSERT          actiune.sendKeys(keys.INSERT).perform()
    PAGEUP          actiune.sendKeys(keys.PAGEUP).perform()
    PAGEDOWN        actiune.sendKeys(keys.PAGEDOWN).perform()
    END             actiune.sendKeys(keys.END).perform()
    HOME            actiune.sendKeys(keys.INSERT).perform()
    SPACE           actiune.sendKeys(keys.SPACE).perform()

*/
//  Cautam butonul COMPARE! si apasam pe el:

        driver.findElement(By.id("compareButton")).click();
//      (By.xpath("//*[@id='compareButton']/div[1]")
        Thread.sleep(2500);

    Thread.sleep(3500);
    driver.close();
    driver.quit();

    }
}