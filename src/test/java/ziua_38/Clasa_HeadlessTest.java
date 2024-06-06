package ziua_38;
/*
    Headless testing - folosinm aceasta metoda putem fara UI sa executam test case-uri in backend si are avantajul ca
    sunt foarte rapide si performantele sunt foarte bune comparativ cu UI.
    Ca dezavantaje avem ca nu putam sa ii explaram functionalitatea, nu vedem actiunile si validarile.

    In acest exercitiu nu folosim DriverManager pentru definirea driver-ului.

    Pentru a putea avea Headless mode o sa folosim ChromeOptions - este o clasa creata pt optiuni.
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Clasa_HeadlessTest {

    public static void main(String[] args) throws InterruptedException {

//--------------------- ACESTEA SUNT PENTRU CHROME BROWSER

//// Aici definim clasa ChromeOptions - cu metoda 1 care nu functioneaza
//        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
//
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver(options); // Utilizăm opțiunile configurate


// Aici definim clasa ChromeOptions - cu metoda 2 si apelam direct WebDriver
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = WebDriverManager.chromedriver().capabilities(options).create();

        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");
        Thread.sleep(3000);


/*--------------------- ACESTEA SUNT PENTRU EDGE BROWSER
Metoda - M1
        EdgeOptions options = new EdgeOptions();
        optionsd.setHeadless(true);

        WebDriverManager.edgedriver().setup();
        WebDriver driver = EdgeDriver(options);

Metoda - M2
        EdgeOptions options = new EdgeOptions();
        optionsd.setHeadless(true);
        WebDriver driver = WebDriverManager.edgedriver().capabilities(options).create();

*/



//  Inseram elementele in casute si dam click pe butonul de login
//  Mai jos am prezentat ambele variante - cu WebElement si fara ca sa aratam diferenta de functionare
        WebElement usertextbox = driver.findElement(By.name("username"));
        usertextbox.sendKeys("Admin");

        driver.findElement(By.name("password")).sendKeys("admin123");

        driver.findElement(By.xpath("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();

        // Se verifica titlul paginii de HRM Orange care este: OrangeHRM
        String titlu_existent = driver.getTitle();
        String titlu_asteptat = "OrangeHRM";

        if(titlu_existent.equals(titlu_asteptat)) {
            System.out.println("Logarea a reusit");
        } else {
            System.out.println("Logarea a esuat");
        }

        driver.close();
        driver.quit();
    }
}