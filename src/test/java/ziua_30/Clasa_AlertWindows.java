package ziua_30;

import drivers.DriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Clasa_AlertWindows extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//        WebDriverWait my_wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(1000);


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//  Prima adat ne ducem si verificam butonul de "Click for JS Alert"
        driver.findElement(By.xpath("//button[normalize-space()=\"Click for JS Alert\"]")).click();
        Thread.sleep(1000);
        Alert alertwindow1 = driver.switchTo().alert();
//        Alert alertwindow1 = my_wait.until(ExpectedConditions.alertIsPresent());      // este comentat impreuna cu linia de my-wait
        System.out.println(alertwindow1.getText());
        Thread.sleep(1000);

        alertwindow1.accept();       // acceptam fereastar respectiva si va inchide fereastra utilizand OK button
        Thread.sleep(1000);


        driver.findElement(By.xpath("//button[normalize-space()=\"Click for JS Prompt\"]")).click();
        Thread.sleep(1000);

        Alert alertwindow2 = driver.switchTo().alert();
        System.out.println(alertwindow2.getText());

//  Deci inseram text in casuta
        alertwindow2.sendKeys("Welcome");
        Thread.sleep(1000);
//  Si apasam prin comanda de mai jos validarea
        alertwindow2.accept();
        Thread.sleep(1000);
/*  Astfel primim in linia de etichete o eticheta care este de natura WeElement i se paote inspecta, de unde facem
    captura in inner text
 */
//  Validarea se face:

        String act_text = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expect_text = "You entered: Welcome";
        Thread.sleep(1000);

//  Facem o comparatie intre textul gasit si cel astepatat sa apara:
        if(act_text.equals(expect_text))
        {
            System.out.println("Testul a trecut, valoarea e corecta");
        }
        else
        {
            System.out.println("Testul a picat, valoare incorecta");
        }

/*  Alert este o interfata si nu o clasa, deci ea o sa fie implementat pri alte clase.
    Deci nu avem nevoie sa generam o clasa de alerte ca mai jos:

    Alert alerta = new Alert();

 */




        Thread.sleep(3000);
        driver.quit();

    }
}
