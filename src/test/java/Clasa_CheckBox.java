// Aceasta clasa trateaza problema apelarii, afisarii si inspectiei checkbox-urilor

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_CheckBox extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  Folosim un IMPLICIT wait de 5 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((5)));
        driver.manage().window().maximize();

//        driver.get("https://itera-qa.azurewebsites.net/home/automation");
//        driver.get("https://automationintesting.com/selenium/testpage/");
        driver.get("http://test.rubywatir.com/index.php");
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(2000);


        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
//  Ca sa avem acces la pagina de checkbox se da click pe icoana "Checkboxes" din Level1
        driver.findElement(By.xpath("//*[@id=\"recent4\"]/ul/li[2]/a")).click();

        List<WebElement> boxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        System.out.println("Avem un numar de casute = " + boxes.size());
        Thread.sleep(1000);

/*  Cum selectam toate box-urile?
    Intai verificam daca este bifata sau nu, daca este bifta trece la casuta urmatoare
    Daca nu este bifata o bifeaza si trece la casuta urmatoare.
 */

//  Varianat cu iteratorul i:
//        for(int i=0; i<boxes.size(); i++)
//        {
//            if (!boxes.get(i).isSelected())
//            {// a fost verificat daca casuta e bifata sau nu si le bifam pe cele neselectate}
//                boxes.get(i).click();
//            }
//            Thread.sleep(500);  // avem aceasta interziere intre verificarile /bifarile casutelor
//        }


//  Sau modelul simplificat unde nu folosim iteratorul i:
        for (WebElement box : boxes) {
            if (!box.isSelected()) { // a fost verificat daca casuta e bifata sau nu si le bifam pe cele neselectate}
                box.click();
            }
            Thread.sleep(500);  // avem aceasta interziere intre verificarile /bifarile casutelor
        }
        System.out.println("Un numar de " + boxes.size() + " casutele au fost bifate");
        Thread.sleep(2000);


/*  Cum selectam ultimele 2 box-urile si toate de dinainte sa raman nebifate?
    Intai verificam daca este bifata sau nu. Daca este bifta o debifam si apoi trece la casuta urmatoare
    Daca nu este bifata sarim peste ea si trecem la casuta urmatoare.
    Pt ultimele 2 casute facem exact invers.
    Deci reincarcam pagina si verificam iar - driver.navigate().refresh();
*/
        driver.navigate().refresh();
        List<WebElement> boxes1 = driver.findElements(By.xpath("//input[@type='checkbox']"));
        Thread.sleep(1000);
        for(int j=0; j<boxes1.size(); j++)
        {
            if ( j<6 && boxes1.get(j).isSelected())
            {// a fost verificat daca casuta e bifata si le bifam pe cele nebifate}
                boxes1.get(j).click();
            } else if ( j>=6 && j<8 && !boxes1.get(j).isSelected()){
                boxes1.get(j).click(); // Bifăm ultimele 2 casete care nu sunt bifate
            }
            Thread.sleep(500);
        }
        System.out.println("Au ramas bifate ultimele 2 casute");
        Thread.sleep(2000);
        driver.quit();
    }

}
