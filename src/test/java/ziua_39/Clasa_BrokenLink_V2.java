package ziua_39;

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

public class Clasa_BrokenLink_V2 {

    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Obține lista de ecrane disponibile
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        // Alege ecranul dorit (de exemplu, ecranul 1 care este = 0 sau 2 care este = 1)
        int screenIndex = 1; // 0 pentru primul ecran, 1 pentru al doilea ecran, etc.
        if (screenIndex >= screens.length) {
            System.out.println("Ecranul specificat nu există.");
            return;
        }
        GraphicsDevice screen = screens[screenIndex];
        Rectangle bounds = screen.getDefaultConfiguration().getBounds();

        // Maximizează fereastra browserului și mută pe ecranul selectat
        driver.manage().window().maximize();
        moveBrowserToScreen(driver, bounds);
        Thread.sleep(2000);

        // Deschide URL-ul
        driver.get("http://deadlinkcity.com");
        Thread.sleep(2000);

        // Obține handle-ul ferestrei curente a browserului
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Captura a tuturor link-urilor din pagina web
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Numarul total de link-uri : " + links.size());

        int brokenlinks = 0;

        for (WebElement linkElem : links) {
            String attributeLink = linkElem.getAttribute("href");

            // Verificăm dacă atributul href este null sau gol
            if (attributeLink == null || attributeLink.isEmpty()) {
                System.out.println("href attribute is empty!");
                continue;
            }

            // Convertim String în URL și verificăm statusul
            try {
                URL linkURL = new URL(attributeLink);    // String ---> URL format

                // Accesăm conexiunea și trimitem solicitarea
                HttpURLConnection connectURL = (HttpURLConnection) linkURL.openConnection();
                connectURL.setRequestMethod("HEAD");

                connectURL.connect();       // aici ne conectam la server

                if (connectURL.getResponseCode() >= 400) {
                    System.out.println(linkElem.getText() + " ===> Broken Link");
                    brokenlinks++;
                } else {
                    System.out.println(linkElem.getText() + " ===> Not Broken Link");
                }
            } catch (MalformedURLException e) {
                System.out.println(attributeLink + " este un URL malformat: " + e.getMessage());
            }
        }

        System.out.println("Numarul total de link-uri broken: " + brokenlinks);

        Thread.sleep(3500);
        driver.close();
        driver.quit();
    }

    // Metoda pentru mutarea browserului pe ecranul dorit
    private static void moveBrowserToScreen(WebDriver driver, Rectangle bounds) {
        driver.manage().window().setPosition(new org.openqa.selenium.Point(bounds.x, bounds.y));
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(bounds.width, bounds.height));
    }

}
