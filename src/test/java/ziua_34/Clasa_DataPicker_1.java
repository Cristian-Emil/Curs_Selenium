package ziua_34;//  Drop-down element - data-picker , cum le accesam si cum le gestionam.

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_DataPicker_1 extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://jqueryui.com/datepicker/");
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

//  ----------------------------------- aici incepem sa scriem programul -----------------------------------------------
//  activam linia ca sa trecem pe frame-ul pe care avem nevoie - primul frame = 0
        driver.switchTo().frame(0);
        Thread.sleep(1000);

////  prima metoda de abordare - selectam si inseram data dupa care dam click
//        driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys("03/04/2024");
////  Acelasi lucru dar il apelam prin WebElement si apoi inseram cheia
////        WebElement data= driver.findElement(By.xpath("//input[@id='datepicker']"));
////        data.sendKeys("03/04/2024");
//
////  dam click ca sa inchidem fereastra de drop-down cu datele afisate:
//        driver.findElement(By.id("ui-datepicker-div")).click();

//  a doua metoda de abordare:
//  Definim datele ce se insereaza in tabel:
        String anul = "2024";
        String luna = "January";
        String ziua = "3";
/*  Pentru ca nu stim ce luna si an se afiseaza in tabelul drop-down o sa  pune o conditie WHILE(TRUE) - o bucla - pt cautare
    inainte sau inapoi. Cautam in viitor sau in trecut.
*/
        driver.findElement(By.xpath("//input[@id='datepicker']")).click();
        Thread.sleep(1000);

//  Capturam luna si anul din drop-down tabel cu bucla WHILE:
        while(true)
        {
            String luna_tabel = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
            String an_tabel = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
//  Deci am facut captura de LUNA si AN pe care le comparam cu ce dorim noi sa afisam:
            if(luna.equals(luna_tabel) && anul.equals(an_tabel))
            {
                break;
            }
//           driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();            // pentru data in viitor
            driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-w']")).click(); // pentru data in trecut

            System.out.println("Anul = " + an_tabel + "\n" + "Luna = " + luna_tabel);
        }
        Thread.sleep(1000);

/*  Acum trebuie sa selectam si ziua despre care vorbim si vedem ca la unele luni primele zile sunt libere
    1. Definim tabelul care are un XPATH :          //table[@class='ui-datepicker-calendar']
    2. Definim randul de pe care copiem valoarea:   //tbody/tr      - unde dupa tr nu am pus paranteze ca vrem sa verificam tot randul
    3. Selectam data din cele 7 care sunt pe rand:  /td
deci intregul nostru XPATH este format din :        //table[@class='ui-datepicker-calendar']//tbody/tr/td
sau putem sa-l simplificam :                        //table[@class='ui-datepicker-calendar']//td
 */

        List<WebElement> toate_datele = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody/tr/td"));
//  Acum ca am gasit toate dalele o sa le comparam cu valoare de care avem noi nevoie:
        Thread.sleep(1000);

////  Varianta 1 de cautare a datei - cu iteratie in bucla for si if pana primim raspunsul cautat:
//        for(WebElement data : toate_datele)
//        {
//            if(data.getText().equals(ziua))
//            {
//                data.click();
//                break;
//            }
//            Thread.sleep(1000);
//            System.out.println("Ziua = " + data.getText());
//        }

//  Varianta 2 de cautare a datei - cu iteratie in bucla for-if pana primim raspunsul cautat:
        for( int i =0; i<=toate_datele.size(); i++)
        {
            if(toate_datele.get(i).getText().equals(ziua))
            {
                toate_datele.get(i).click();
                break;
            }
            System.out.println("Ziua = " + toate_datele.get(i).getText());
            Thread.sleep(1000);
        }
//  Daca in data-picker avem drop-down box pentru luna si an puterm sa inseram direct valorile si sa le citim din drop-box



    Thread.sleep(5000);
    driver.quit();
    }
}
