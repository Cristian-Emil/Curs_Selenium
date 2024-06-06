package ziua_35;
/*
Vedem cum se poate da un DUBLU CLICK
Double Click - doubleClick(Element)

Vezi mai jos diferenta intre getText() si getAttribute("value")
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Set;

public class Clasa_DoubleClick {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("http://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_ondblclick3");

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
//  Eliminam pagina de acceptare si dam un click pe ea in sectiounea "Accept all" .
        Thread.sleep(500);
        driver.findElement(By.id("accept-choices")).click();
        Thread.sleep(500);

//  Si facem switch pe frame
        driver.switchTo().frame("iframeResult");
        Thread.sleep(500);
//  Capturam elementul, stergem ce este in casuta1 / box1 si inseram textul "Welcome"
        WebElement box1 = driver.findElement(By.xpath("//*[@id='field1']"));
        Thread.sleep(500);
        box1.clear();
        Thread.sleep(500);
        box1.sendKeys("Welcome");
        Thread.sleep(1000);

//  Trebuie sa duble-click pe butonul COPY TEXT, deci il facem WebElement din driver.findEleemnt. Deci o sa facem actiunea
        WebElement buton_copy = driver.findElement(By.xpath("/html/body/button"));
        Thread.sleep(1000);

        Actions actiune = new Actions(driver);
        actiune.doubleClick(buton_copy).perform();      // aceasta face dublu click pe butonul nostru

//  Dupa ce am facut dublu_click si in casuta2 / box2 a aparut textul trebuie sa-l capturam si-l stocam intr-un string
        WebElement box2 = driver.findElement(By.xpath("//*[@id='field2']"));
        Thread.sleep(1000);

        String text_box = box2.getText();
        System.out.println("Textul din casuta2 este - " + text_box);
//  Se observa ca folosind metoda getText() aici nu functioneaza, deci folosim alta varianta - getAttribute:
        String text_box2 = box2.getAttribute("value");
        System.out.println("Textul din casuta2 este - " + text_box2);

/*
Diferenta dintre getText() si getAttrinute()
- aici avem un innertext care mai jos eate exact cuvantul testting
<input id='abc'> testing</input>
findElement(Loc).getText()  - - - - acesta ne va returna testing
findElement(Loc).getAttribute('value')  - - - - - acesta nu va functiona pt ca nu are value atribute si nu returneaza ceva

mai jos nu avem innertext
<input id='abd' value = 'testing'/>
findElement(Loc).getText()   - - - - - acesta nu va functiona pt ca nu are value atribute si nu returneaza ceva
findElement(Loc).getAttribute('value')   - - - - acesta ne va returna testing

deci avem concluzia:
getText()               ----- va returna textul din element (innertext = textul din interior)
getAttribute("value")   ----- va returna valuarea testing
getAttribute("id")      ----- va returna valuarea abc, adica exact id-ului

pentru a vedea mai bine o sa urmarim ce fac cele 2 elementele, facem acealsi lucru pt box1 dar cu getText si constatam
ca nu functioneaza si pentru a avea rezultat pozitiv folosim getAttribute
Elementul din casuta este creat in timp ce rulam programul - runTime - deci nu se paote copia cu getText pt ca valorile
sunt create cu VALUE in acest caz
 */

        System.out.println("");
//  Am tiparit un rand liber ca sa facem diferenat intre cele 2 din box1:
        String text_box_1 = box1.getText();
        System.out.println("Textul din casuta1 este - " + text_box_1);

        String text_box1 = box1.getAttribute("value");
        System.out.println("Textul din casuta1 este - " + text_box1);

        System.out.println("");
//  facem verificarea sa vedem daca avem aceleasi valori in cele 2 casute:
        if(text_box2.equals("Welcome"))
        {
            System.out.println("Testul a trecut");
        }
        else
        {
            System.out.println("Testul a picat, valorile din box1 si box 2 nu sunt egale ");
        }

//  Daca dorim sa pice testul, nu se poate face automat

    Thread.sleep(5000);
    driver.quit();
    }
}
