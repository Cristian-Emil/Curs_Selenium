package ziua_31;//  Realizam o verificare pentru dropDown fara selector

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_DropDownNoTag extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://www.dropbox.com/");
//        driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0.2");
//        System.out.println(driver.getCurrentUrl());
        Thread.sleep(1000);


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        driver.findElement(By.xpath("//*[@id=\"warp-metadata\"]/nav/div[2]/div[3]/a/div/span")).click();
        Thread.sleep(500);
//  Inchidem bannerul cu ACCEPT ALL din josul paginii pt ca ne fura click-ul.
//        driver.findElement(By.id("accept_all_cookies_button")).click();
//        Thread.sleep(500);

//  Reveni pe pagina si introducem valorile:
        driver.findElement(By.cssSelector("#susi_email30160328196336583")).sendKeys("cristianzidarescu@hotmail.com");
        Thread.sleep(500);

        driver.findElement(By.xpath("//*[@id=\"login-or-register-page-content\"]/div/div/div/div/div/div/div[2]/form/div[2]/div/button")).click();
        System.out.println(" ");

        Thread.sleep(2500);
        driver.quit();
    }
/*
Pagina pe care se lucra initial a fost modificata si acel DROPDOWN cu optiuni selectabile a fost modificat, am comentat
toate aceste verificari si am mutat mai jos tot pachetul:

//  click in dropdown
    driver.findElement(By.xpath("//button[contains(@class,'multiselct')]")).click();
    List<WebElement> options = driver.findElements(By.xpath("//ul[contains(@class,multiselect)]//label"));

//  cautam numarul total de optiuni:
    System.out.println("Numarul total de optiuni " +options.size());

//  tiparim numarul total de optiuni
    for(int i=0; i<options.size(); i++)
    {
//      comentan prima varianta
//        if(options.get(i).getText().equals("Java"))
//        {
//            options.get(i).click();
//            break;
//        }

//  aplicam a doua varianta
        if(options.equals("Java") || options.equals("Python"))
        {
            options.get(i).click();
        }
    }

    for(WebElement option:options)
    {
        String tesxt = options.getText();
        if(text.equalsd("Java") || text.equals("Python"))
        {
            options.click());
        }
    }
 */

}
