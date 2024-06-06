package ziua_21;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;
import java.time.Duration;

public class Clasa_primaClasa {

    public static void main(String[] args) throws InterruptedException {

//  Pentru Chrome avem linia
        WebDriver driver =new ChromeDriver();

//  Pentru Edge avem linia
//        WebDriver driver =new EdgeDriver();

//  Pentru Fire Fox avem linia
//        WebDriver driver =new FireFoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://demo.opencart.com/admin/");

        Thread.sleep(2500);

        String titlu= driver.getTitle();

        if (titlu.equals("OpenCart"))
        {
            System.out.println("Testul a trecut");
        }
        else
        {
            System.out.println("Testul a esuat");
         }

    driver.close();
    driver.quit();

//  Testul va esua pentru ca am pus un text care nu este afisatasa pe pagina.
    }
}
