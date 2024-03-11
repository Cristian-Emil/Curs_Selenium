package ziua_27;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_PaginationTable extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://demo.opencart.com/admin/index.php");
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

//  Ne logam , curatam casutele si apoi inseram ce avem noi nevoie
        WebElement username = driver.findElement(By.id("input-username"));
        username.clear();
        Thread.sleep(1000);
        username.sendKeys("demo");

        WebElement password = driver.findElement(By.id("input-password"));
        password.clear();
        Thread.sleep(1000);
        password.sendKeys("demo");
        Thread.sleep(1000);
//  Apasam pe butonul de Login
        driver.findElement(By.xpath("//button[text()=' Login']")).click();
        Thread.sleep(1000);

//  Din cand in cand ne apare o pagina de CloudFlare pe care trebuie s-o inchidem :
//        if(driver.findElement(By.xpath("//*[@id=\"challenge-stage\"]/div/label/input")).isDisplayed())
//        {
//            driver.findElement(By.xpath("//*[@id=\"challenge-stage\"]/div/label/input")).click();
//        }
//        Thread.sleep(5000);

//  Ne apare o fereastra de confirmare pe care o inchidem dand click pe X-ul din dreapat sus al ei
//  O variante de XPATH ca sa inchidem fereastra este  - (By.xpath("//*[@id=\"modal-security\"]/div/div/div[1]/button")
//  O alta variante de XPATH ca sa inchidem fereastra este  - (By.xpath("//button[@class='btn-close']")

        if(driver.findElement(By.xpath("//*[@id=\"modal-security\"]/div/div/div[1]/button")).isDisplayed())
        {
            driver.findElement(By.xpath("//*[@id=\"modal-security\"]/div/div/div[1]/button")).click();
        }

//  Avem urmatorul Customers din primul Customers - se constat ca pagina a fost modificat , deci moddifcam si xpath-ul
//        driver.findElement(By.xpath("//a[class='parent collapsed'][normaliza-space()='Customers']")).click();
        driver.findElement(By.xpath("//*[@id=\"menu-customer\"]/a")).click();
//        driver.findElement(By.xpath("//a[class='collapse show']//a[contains(text),'Customers')]")).click();
        driver.findElement(By.xpath("//*[@id=\"collapse-5\"]/li[1]/a")).click();
        Thread.sleep(1000);


        String text=driver.findElement(By.xpath("//*[@id=\"form-customer\"]/div[2]/div[2]")).getText();

//  text.substring(27, 31);   // acestea sunt valori statice si nu se vor modficia impreuna cu tabelul deci nu se pot utiliza aici

//  Noi citim pagini din 9 in 9 pana la xxx - adica xxx pagini - care sunt dimanice. Ele pot varia in timp
/*  Deci constatam ca :
        //Showing 91 to 100 of 18411 ( 1841 pages)
        //Showing 91 to 100 of 1010 ( 110 pages)
        //Showing 91 to 100 of 1841 ( 185 pages)

    Se observa ca numerele sunt dinamice dar "( pages)" sunt fixe . Deci se va face verificare dupa acestea considerand
    partea fixa si ce dinamica
    (xxx pages)
    (xx pages )
    (xxxx pages)

    valorile xxx, xx si xxxx sunt variabile dar paranteza "("  si "pages)" sunt fixe. Deci acestea se vor considera in
    evaluare
*/

        int total_pagini = Integer.parseInt(text.substring(text.indexOf("(")+1, text.indexOf("Pages")-1));
        System.out.println("Numarul total de pagini = " + total_pagini);

        for(int p=1; p<=5; p++)
        // daca dorim sa verificam toate cele xxx pagini folosim comanda for(int p=1;p<=total_pagini; p++) in locul celei cu 5
        {
            if (total_pagini>1)
            {
                WebElement active_pe_pagina=driver.findElement(By.xpath("//ul[@class='pagination']//li//*[text()="+p+"]"));
                System.out.println("Pagini active = " + active_pe_pagina.getText());
                active_pe_pagina.click();
                Thread.sleep(2000);
            }
        }
/*  Deci mai sus am reusit sa afisam numarul de pagini care acum este 1841 dar el se schimba in timp. \deci paginatia
    este dinamica. Deci
*/

//  Acum trebuie sa identificam numarul de
        int nr_randuri = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover']//tbody//tr")).size();
        System.out.println("Total nr randuri active pe pagina = " + nr_randuri);
        Thread.sleep(1000);

        for(int r=1; r<=nr_randuri; r++)
        {
        String customer_name = driver.findElement(By.xpath("//*[@id=\"form-customer\"]/div[1]/table/thead/tr/td[2]/a")).getText();
        String e_mail = driver.findElement(By.xpath("//*[@id=\"form-customer\"]/div[1]/table/thead/tr/td[3]/a")).getText();
        String customer_group = driver.findElement(By.xpath("//*[@id=\"form-customer\"]/div[1]/table/thead/tr/td[4]/a")).getText();

        System.out.println("Numarul de nume =  " + customer_name);
        System.out.println("Numarul de email-uri =  " + e_mail);
        System.out.println("Numarul de grupuri =  " + customer_group);
        }

    Thread.sleep(5000);
    driver.quit();
    }
}
