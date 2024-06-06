package ziua_36;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_TabsAndSwitchWindows {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

//  acest model deschide primul URL in tab-ul pornit si apoi in acelasi tab deschide al doilea URL
//        driver.get("http://opencart.com");
//        driver.get("http://opensource-demo.orangehrmlive.com.com");

//  ideea este sa deschida primul URL in primul tab dupa care sa deschida al doilea tab unde sa deschida al doilea URL
        driver.get("http://opencart.com");

//  pentru asta facem switch pe al doilea tab cu comanda "switchTo()" si inseram URL-ul dorit
        driver.switchTo().newWindow(WindowType.TAB);                //  aceasta comanda ne deschide un nou tab.
        driver.get("http://opensource-demo.orangehrmlive.com.com");

//  ATENTIE : daca vrem sa deschidem un nou BROWSER folosim comanda de mai jos
//        driver.switchTo().newWindow(WindowType.WINDOW);                //  aceasta comanda ne deschide un nou tab.

//  deci daca specificam TAB o sa deschida un nou TAB, daca scriem WINDOW deschide un brouser nou.

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

//  ca sa putem sa ne plimbam intre tab-uri realizam o functie separata cu care ne plimba intre ele:

//  ne intoarcem la primul URL si stam 2,5 secunde in el
        switchToTab(driver, 0);
        Thread.sleep(2500);

//  ne intoarcem la al doilea URL si stam 2,5 secunde in el
        switchToTab(driver, 1); // Comută la al doilea tab
        Thread.sleep(2500); // Așteaptă 2 secunde pentru a vedea comutarea

//  ne intoarcem la primul URL si stam 2,5 secunde in el
        switchToTab(driver, 0); // Comută la primul tab
        Thread.sleep(2500); // Așteaptă 2 secunde pentru a vedea comutarea

//  ne intoarcem la al doilea URL si stam 2,5 secunde in el
        switchToTab(driver, 1); // Comută la al doilea tab
        Thread.sleep(2500); // Așteaptă 2 secunde pentru a vedea comutarea

    }

// mai jos definm metoda prin care facem switch intre cele doua ecrane.
    public static void switchToTab(WebDriver driver, int tabIndex) throws InterruptedException {
        Set<String> windowHandles = driver.getWindowHandles();
        String[] handles = windowHandles.toArray(new String[0]);
        if (tabIndex < windowHandles.size()) {
            driver.switchTo().window(handles[tabIndex]);
        } else {
            throw new IllegalArgumentException("Tab index in afara limitelor ");
        }


        Thread.sleep(3500);
        driver.close();
//  inchidem driverele dupa utilizare
        driver.quit();

/*  ATENTIE:
    JavaScriptExecutor --- este o interfata care executa anumite comenzi.
    executeScript() --- cu aceasta putem executa declaratiile JavaScript in metodele Selenium.

utilizat pentru testare automata si verificari:
https://testautomationpractice.blogspot.com/
*/

    }
}
