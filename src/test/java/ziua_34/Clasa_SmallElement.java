package ziua_34;//  Cum gasim cel mai mic element dintr-un sir

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_SmallElement extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://blazedemo.com/");


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

//  ----------------------------------- aici incepem sa scriem programul -----------------------------------------------
// Vedem cum putem sa alegem cea mai mica valoare dintr-un sir
        int a[] ={450, 400, 550, 300, 600, 150, 200, 700};
        Arrays.sort(a);

        for (int x:a)
        {
            System.out.println(x);
        }
        System.out.println(" ");
        System.out.println("Tiparim cel mai mic numar x = " +a[0]);

        System.out.println("$" + a[0]);
        String price = "$200,5";
//  Eliminam semnul $ din fata textului:
        String pret =price.replace("$","");
        System.out.println(pret);


//----------------------------------------------------------------------------------------------------------------------
//  Dupa ce intram pe pagina dam click pe FIND FLIGHT si intram in tabel si ne alegem zborul care ne place:

        driver.findElement(By.xpath("//select[@name='fromPort']")).sendKeys("Boston");
        Thread.sleep(500);
        driver.findElement(By.xpath("//select[@name='toPort']")).sendKeys("London");
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@value='Find Flights']")).click();
        Thread.sleep(500);

//  Incepem sa selectam valorile din tabel:
        driver.findElement(By.cssSelector("body > div.container > table > tbody")).click();
        Thread.sleep(500);
//  Cautam valorile din tabel si le comparam ca sa vedem care e valoarea cea mai mica. r = randuri , c= coloane
        List<WebElement> randuri = driver.findElements(By.xpath("/html/body/div[2]/table/tbody//tr"));
        int r = randuri.size();

        List<WebElement> coloane = driver.findElements(By.xpath("/html/body/div[2]/table/tbody//td"));
        int c = coloane.size();
        Thread.sleep(500);
        System.out.println("Numarul de randuri este = " + r + "\n");
        System.out.println("Numarul de coloane este = " + c + "\n");
        Thread.sleep(500);
//  Acesta tipareste doar valoarea primului bilet:
        String valoare = driver.findElement(By.xpath("//html/body/div[2]/table/tbody//td[6]")).getText();
        System.out.println(valoare+ "\n");    // pretul biletelor de pe coloana 6
        Thread.sleep(500);
//  Iteram ca sa avem valoarea tuturor zborurilor, le tiparim si apoi pumen preturile in matrice:
        String preturi[] = new String [r];

        for(int x=1; x<=r; x++)
        {
            String valoare1 = driver. findElement(By.xpath("//html/body/div[2]/table/tbody//tr["+x+"]/td[6]")).getText();
//                                    avem un xpath:    //table[@class='table']//tbody//tr["+r+"]/td[6]
            System.out.print(valoare1 + "\n");
            preturi[x-1]=valoare1;
        }
        System.out.println("");
        Thread.sleep(500);

//  Acum cand am tiparit preturile le comparam si alegem pretul mai mic:
//
        for(String pret_bilet:preturi)
        {
            System.out.println(pret_bilet);
        }
        System.out.println("");

        Arrays.sort(preturi);               // acesta este capabil sa sorteze siruri deci nu le transformam in numerer
        String pret_mic=preturi[0];
        System.out.println("Cel mai ieftin bilet : " + pret_mic);
        Thread.sleep(500);

//  Cautam in tabel carui zbor ii corespunde cel mai mic pret si dam click pe acesta pentru selectare:
        for(int x=1;x<=r; x++)
        {
            String valoare1 = driver. findElement(By.xpath("//html/body/div[2]/table/tbody//tr["+x+"]/td[6]")).getText();
//                                      avem un xpath:    //table[@class='table']//tbody//tr["+x+"]/td[6]
            if(pret_mic.equals(valoare1))
            {
                driver.findElement(By.xpath("//table[@class='table']//tbody//tr["+x+"]/td[1]//input")).click();
                break;
            }
        }
        Thread.sleep(2500);
//  Dupa ce am intrat in zborul cu cel mai mic pret inseram valorile pentru a cumpara biletul:

        driver.findElement(By.id("inputName")).sendKeys("Vasile");
        driver.findElement(By.id("address")).sendKeys("Strada Aplecata nr.9");
        driver.findElement(By.id("city")).sendKeys("Localitatea X");
        driver.findElement(By.id("state")).sendKeys("Acesta");
        driver.findElement(By.id("zipCode")).sendKeys("70000");
        driver.findElement(By.id("creditCardNumber")).sendKeys("1234567849101112");
        driver.findElement(By.id("creditCardYear")).clear();
        driver.findElement(By.id("creditCardYear")).sendKeys("2022");
        driver.findElement(By.id("nameOnCard")).sendKeys("Vasile AluiIon");
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();

// Si validam biletul:
        String msg=driver.findElement(By.xpath("//h1")).getText();

        if(msg.contains("Thank you for your purchase"))
        {
            System.out.println("Succes !! Biletul a fost achizitionat");
        }
        else
        {
            System.out.println("Eroare ... de paralaxa");
        }

    Thread.sleep(5000);
    driver.quit();

    }
}
