package ziua_38;
/*
    ATENTIE:
    Folosirea comenzilor Robot și AWT nu asigura mereu alegerea ecranului dorit si aceasta provine din faptul că aceste
    metode nu sunt destinate controlului direct al ferestrelor browserului WebDriver. AWT și Robot nu au acces direct la
    fereastra browserului creată de WebDriver și scriptul JavaScript utilizat pentru a obține fereastra nu va funcționa
    așa cum este așteptat.
*/



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.time.Duration;
import java.util.Set;

public class Clasa_SelectareEcran_1_2_V2 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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

        // Deschide browserul și maximizează fereastra
        driver.manage().window().maximize();
        driver.get("http://deadlinkcity.com");

        // Obține handle-ul ferestrei curente a browserului
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Așteaptă puțin pentru a se asigura că fereastra browserului este deschisă
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Utilizează AWT pentru a muta fereastra browserului
        moveBrowserToScreen(driver, bounds);
    }

    private static void moveBrowserToScreen(WebDriver driver, Rectangle bounds) {
        // Obține handle-ul ferestrei browserului utilizând AWT
        try {
            Robot robot = new Robot();
            robot.mouseMove(bounds.x, bounds.y);  // Mută mouse-ul pentru a activa fereastra
            Thread.sleep(500);  // Așteaptă puțin

            // Utilizează clase din AWT pentru a muta fereastra browserului
            java.awt.Window awtWindow = (java.awt.Window) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return window;");

            if (awtWindow != null) {
                awtWindow.setLocation(bounds.x, bounds.y);
                awtWindow.setSize(bounds.width, bounds.height);
            } else {
                System.out.println("Nu s-a putut obține handle-ul ferestrei browserului.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
