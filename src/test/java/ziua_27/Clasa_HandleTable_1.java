package ziua_27;// Tabele statice - valorile sunt fixe, pozitiile sunt fixe.

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_HandleTable_1 extends DriverManager {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
/*  ---------------------- aici incepem sa scriem cerintele programului ------------------------------------------------

- gasiti numarul total de randuri din tabel
- gasiti numarul total de coloane din tabel
- cititi o valoare anume dintr-un rand si o coloana
- cititi valorile din toate randurile si coloanele
- tiparit continutul al carui autor este Amit
- gasiti, calculati si tipariti valoarea totala a cartilor.
 */
//  Incepem cu punctul 1 si 2 din cerinte - numarul de randuri si coloane
//  Cautam numarul de randuri - avem diferite moduri de apelare ale acestor elemente - alegem motoda 1 sau 2

        int randuri1 = driver.findElements(By.xpath("//table[@name='BookTable']//tr")).size(); // Metoda 1
        int randuri2 = driver.findElements(By.tagName("tr")).size();                                        // Metoda 2

        System.out.println("Numarul de randuri este = " + randuri1);
        System.out.println("Numarul de randuri este = " + randuri2);

//  Pentru verificare alegem o a treia varianta pentru a confirma :
        List<WebElement> randuri = driver.findElements(By.xpath("//table[@name='BookTable']//tr"));
        int a = randuri.size();

/*  Vedem ca obtinem valori diferite si asta din cauza ca a doua apelare e dupa eticheta tr si acesta apeleaza toate
    etichetele valide pe pagina - aici 14
    Deci prima abordare este cea corecta.
 */

//  Cautam numarul de coloane valabile pe pagina:

        int coloane1 = driver.findElements(By.xpath("//table[@name='BookTable']//th")).size();
        int coloane2 = driver.findElements(By.tagName("th")).size();

        System.out.println("Numarul de coloane este = " + coloane1);
        System.out.println("Numarul de coloane este = " + coloane2);
        System.out.println("");

//  Pentru verificare alegem o a treia varianta pentru a confirma :
        List<WebElement> coloane = driver.findElements(By.xpath("//table[@name='BookTable']//th"));
        int b = coloane.size();

/*  Vedem ca obtinem valori diferite si asta din cauza ca a doua apelare e dupa eticheta th si acesta apeleaza toate
    etichetele valide pe pagina - aici 8
    Deci prima abordare este cea corecta.
*/
        System.out.println(" Afisam valorile corecte : ");
        System.out.println("Numarul de randuri este = " + a + "\n");
        System.out.println("Numarul de coloane este = " + b + "\n");


//  Continuam cu punctul 3 din cerinte - cautam denumire SELENIUM in tabele, o capturam si o afisam - rand 5 coloana 1
        String valoare1 = driver.findElement(By.xpath("//table[@name='BookTable']//tr[5]//td[1]")).getText();
        System.out.println(valoare1+ "\n");    // Master In Selenium - cu acel \n trecem la randul urmator

//  Continuam cu punctul 4 din cerinte - citim valorile din toate randurile si coloanele - deschidem o conditie FOR
        for(int r=2; r<=randuri1; r++)
        {
            for(int c=1; c<= coloane1; c++)
            {
                String valoare2 = driver. findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]//td["+c+"]")).getText();
                System.out.print(valoare2 + "\t" +"\t");
            }
            System.out.println("");

/*  Ca sa putem sa scriem valorile r si c in xpath cum am pus valorile 5 si 1
            "//table[@name='BookTable']//tr[5]//td[1]"
    avem nevoei sa punem intre ghilimele duble si sa adaugam + ianinte si dupa acestea
            "//table[@name='BookTable']//tr["+r+"]//td["+c+"]"
    deci observati diferenta, cand avem valori fixe si cand avem variabile care nu se pot exprima direct in xpath
    Aici "+" este parte de sintaxa siu o conacatenare de tiparire.
    La prima tiparire am folosit System.ou.print - care tipareste pe acelasi rand
    Inseram un rand liber prin acel System.out.println din afara parantezei mici dar in interiorul primei bucle si
    incepe iar procedura de iterare astfel icnat sa fisam un tabel
    Pentru a pastra distantele egale intre colaone se adauga un TAB - este acel "\t" din partea tiparita
 */
        }
        System.out.println("");
//  Continuam cu punctul 5 din cerinte - tiparit continutul din casuta al carui autor este Amit
// se vede ca Amit apare in coloana 2 din tabelul cautat , deci aven un xpath de forma : td[2] - ca este coloana 2

        for(int r=2; r<=randuri1; r++)
        {
            String autor = driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[2]")).getText();
            System.out.println("Numele autorului este " + autor );

/*      Pana aici am extras toate numele din tabel, de aici incepem sa le comparam cu ce avem noi nevoie, adica sa tiparim
        numele cartii autorului Amit.
        Deschidem bucla IF cu care facem comparatia pt a determina in coloana cu numele cartilor - coloana 1 - care sunt ale lui Amit
*/
            if(autor.equals("Amit"))
            {
                String nume_carte = driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[1]")).getText();
                System.out.println("Autorul si cartea : " + autor + " - " + nume_carte);
                System.out.println("");
            }
        }
        System.out.println("");
/*  Continuam cu punctul 6 din cerinte - gasiti, calculati si tipariti valoarea totala a cartilor.
    Atentie ca pretul este un numar. Intai il transformam in STRING si apoi sa-l transformam iar in INT pt a putea
    sumariza valorile citite. Deci ne referim la coloana 4 - td[4].
    Folosim functia de transformare String in INT => Integer.parseInt(pret)
*/
        int suma =0;
        for(int r=2; r<=randuri1; r++)
        {
            String pret = driver.findElement(By.xpath("//table[@name='BookTable']//tr["+r+"]/td[4]")).getText();
            System.out.println("Pret carte = " + pret);
            suma = suma+Integer.parseInt(pret);
        }
        System.out.println("Pretul total al cartilor  = " + suma);

    Thread.sleep(5000);
    driver.quit();

    }
}
