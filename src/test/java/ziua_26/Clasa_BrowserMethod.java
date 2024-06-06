package ziua_26;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Clasa_BrowserMethod {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(3000);

        driver.manage().window().maximize();
        Thread.sleep(1000);

//  Putem scrie asa pt acrtiune directa:
//        driver.findElement(By.linkText("OrangeHRM, Inc")).click();

//  Putam sa-l scrie diferit ca sa il si tiparim :
        WebElement sigla = driver.findElement(By.linkText("OrangeHRM, Inc"));
        sigla.click();
        Thread.sleep(1000);

        System.out.println(sigla);
        Thread.sleep(1000);

    driver.close();
    driver.quit();
    }
}
