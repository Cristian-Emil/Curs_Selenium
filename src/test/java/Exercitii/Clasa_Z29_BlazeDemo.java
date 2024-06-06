package Exercitii;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.Set;

public class Clasa_Z29_BlazeDemo {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
//  am mutat de aici linia de maximizare ecran la secventa1

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

// secventa 1
        driver.get("https://blazedemo.com");
        driver.manage().window().maximize();
        Thread.sleep(1500);

// secventa 2
        driver.findElement(By.xpath("//select[@name='fromPort']")).sendKeys("Boston");
        Thread.sleep(1000);

// secventa 3
        driver.findElement(By.xpath("//select[@name='toPort']")).sendKeys("London");
        Thread.sleep(1000);

// secventa 4
        driver.findElement(By.xpath("//Input[@value='Find Flights']")).click();
        Thread.sleep(1000);

// secventa 5
        int rand = driver.findElements(By.xpath("//table[@class='table']//tbody//tr")).size();
        System.out.println("Numar de randuri in tabel" + rand);
        Thread.sleep(1000);

// secventa 6 - facem captura de pret si il stocam in matrice
        String pret_total[]=new String[rand];

        for(int r=1; r<=rand; r++)
        {
            String pret1 = driver.findElement(By.xpath("//table[@class='table']//tbody//tr["+r+"]/td[6]")).getText();
            pret_total[r-1]=pret1;  // adasugam pret1 in matrice
        }
        Thread.sleep(1000);

// secventa 7 - sortam preturile de la mic la mare
        for(String matricePret:pret_total)
        {
            System.out.println(matricePret);
        }
        Arrays.sort(pret_total);
        Thread.sleep(1000);

        String pretMinim = pret_total[0];
        System.out.println("Cel mai mic pret este : " + pretMinim);
        Thread.sleep(1000);

// secventa 8 - gasim in tabel pret cel mai mic
        for(int r=1; r<=rand; r++)
        {
            String pret2 = driver.findElement(By.xpath("//table[@class='table']//tbody//tr["+r+"]/td[6]")).getText();

            if (pret2.equals(pretMinim))
            {
                driver.findElement(By.xpath("//table[@class='table']//tbody//tr["+r+"]/td[1]//input")).click();
                break;
            }
        }
        Thread.sleep(1000);

// secventa 9 - se completeaza toate campurile si apoi se apasa pe butonul Purchase Flight

        driver.findElement(By.id("inputName")).sendKeys("Vasile");
        driver.findElement(By.id("address")).sendKeys("str. Verde No.7");
        driver.findElement(By.id("city")).sendKeys("Bucuresti");
        driver.findElement(By.id("state")).sendKeys("Romania");
        driver.findElement(By.id("zipCode")).sendKeys("7000");
        driver.findElement(By.id("creditCardNumber")).sendKeys("1234567890111213");
        driver.findElement(By.id("creditCardYear")).clear();                                    // curatam casuta intai
        driver.findElement(By.id("creditCardYear")).sendKeys("2028");
        driver.findElement(By.id("nameOnCard")).sendKeys("Vasile Bubulinul");
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
        Thread.sleep(1000);

// secventa 10 - validam valorile introduse si operatia de achizitionare
        String mesaj = driver.findElement(By.xpath("//h1")).getText();

        if (mesaj.contains("Multumim pentru alegerea facuta"))
        {
            System.out.println("Succes! Tranzactie reusita");
        }
        else
        {
            System.out.println("Tranzactia a esuat");
        }

    Thread.sleep(3500);
    driver.close();
    driver.quit();

    }
}
