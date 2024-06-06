package ziua_39;

/*
    Pentru a putea discuta despre Broken_Links este necesar ca pagina accesata sa fie definita de o valoare, altfel nu
    se poate vorbi de Broken_Links
    In general in astfel de cazuri serverul furnizeaza o valoare 401, 404  si acesta este tipul de raspuns
    conectat cu cazurile de Broken Links.
    raspuns cu eroare >=400 este broken links - - - (401, 402, 404, 500)
    raspuns cu eroare <= 399 este normal links  - - - (101, 201, 300)

*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_BrokenLinks_WitchSwitchDisplay {

    public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));

/*
    ATENTIE - daca instanța WebDriver pentru browserul Chrome nu respectă locația specificată pentru fereastra creată
    (JFrame) atunci programul o sa deschida o fereastra goala pe ecranul specificat - aici 1 este ecranul 2 - si pagina
    se va deschide cu browserul dorit in locati specificata pe JFrame.
    Pentru a elimina acerst inconvenient se folosesc liniile de mai jos care se pot utiliza ca subrutina.
*/
//----------------------------- selectam ecranul pe care se afiseaza pagina de URL -------------------------------------
// Obține lista de ecrane disponibile
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

//  Alegem ecranul dorit (de exemplu, ecranul 1 care este = 0 sau 2 care este = 1)
        int screenIndex = 1; // 0 pentru primul ecran, 1 pentru al doilea ecran, etc.
        if (screenIndex >= screens.length) {
            System.out.println("Ecranul specificat nu există.");
            return;
        }
        GraphicsDevice screen = screens[screenIndex];
        Rectangle bounds = screen.getDefaultConfiguration().getBounds();

// Utilizează WebDriver pentru a muta și redimensiona fereastra browserului pe al doilea ecran
        moveBrowserToScreen(driver, bounds);
        Thread.sleep(2000);

// Deschide browserul și maximizează fereastra
        driver.manage().window().maximize();
        driver.get("http://deadlinkcity.com");
        Thread.sleep(2000);
//------------------------------------------- partea de focalizare pe pagina -------------------------------------------
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//----------------------------------------------------------------------------------------------------------------------
//  ---------------------------------- aici incepem sa scriem programul ------------------------------------------------
//  Prima data se face captura a tuturor link-urilor din pagina web - toate linkurile au un TAG

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Numarul total de link-uri : " + links.size());

        int brokenlinks=0;
//  pentru orice link trebuie sa avem un atribut ca sa vedem daca e un Broken Link sau NU. Trebuie verificat ca nu sunt goale

        for(WebElement linkElem:links)
        {
            String attributlink = linkElem.getAttribute("href");
            System.out.println(attributlink);
//  punem o conditie de NULL ca sa verificam statusl link-urilor
//  aceasta e pre-requisite form pentru cautarea broken link-urilor
            if(attributlink==null || attributlink.isEmpty())
            {
                System.out.println("href attribute este gol!");
                continue;
            }
//  inainte sa trimitem cererea catre server trebuie sa schimbam forma din STRING in format URL care sa apeleze serverul:
//  Se verifica daca link-ul e broken sau nu !
            URL linkURL = new URL(attributlink);    // String ---> URL format

//  intai accesam connexiunea si apoi trimitem solicitarea
            HttpURLConnection connectURL = (HttpURLConnection) linkURL.openConnection();

            if(connectURL.getResponseCode()>=400)
            {
                System.out.println(linkElem.getText()+" " + " ===> Broken Link");
                brokenlinks++;
            }
            else
            {
                System.out.println(linkElem.getText()+" " + " ===> Not Broken Link");
            }
        connectURL.connect();       // aici ne conectam la server
        }
        System.out.println("Numarul total de broken links : " + brokenlinks);

    Thread.sleep(3500);
    driver.close();
    driver.quit();
    }

//------------------------ aici avem secventa de program pentru mutarea pe ecranul dorit -------------------------------
        private static void moveBrowserToScreen(WebDriver driver, Rectangle bounds) {
// Mută fereastra browserului pe ecranul selectat
            driver.manage().window().setPosition(new org.openqa.selenium.Point(bounds.x, bounds.y));
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(bounds.width, bounds.height));
        }
}
