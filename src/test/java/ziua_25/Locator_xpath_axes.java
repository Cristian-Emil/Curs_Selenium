package ziua_25;/*
Definirea si utilizarea locatorului XPATH-AXES
 */

import drivers.DriverManager;
// import io.github.bonigarcia.wdm.WebDriverManager;    // se preia din clasa de DriverManager si nu web
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

public class Locator_xpath_axes extends DriverManager{

    public static void main(String[] args) throws InterruptedException{

//        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("https://money.rediff.com/gainers/bse/daily/groupa");
        driver.manage().window().maximize();

//  Ne concentram pe pagina deschisa , focalizare pe aceasta
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

/*
Locator xpath cu multiple atribute :
    //input[@name='usernaem'][@placeholder='Username']

    //input[@name='username' and @placeholder='Username'] - aici avem conditia ca ambele solicitari sa primeasca raspuns - AND
    //input[@name='username'or @placeholder='Username'] - aici avem conditia ca una din solicitari sa primeasca raspuns - OR

    avem multiple apelari de atribute in xpath
    last()
    text()
    starts-with()
    contains()
    and
    or

 */

//Self / insusi  - Selects the current node - selecteaza nodul-locatorul curent
        String text=driver.findElement(By.xpath("//a[contains(text(),'NIIT Ltd')]/self::a")).getText();
        System.out.println("Self : "+ text); //NIIT Ltd
        Thread.sleep(1000);

//Parent / aprinte - Selects the parent of the current node (always One) - selecteaza parintele nodului-locatorului curent
        text=driver.findElement(By.xpath("//a[contains(text(),'NIIT Ltd')]/parent::td")).getText();// there is no text for parent, so it is same value display
        System.out.println("Parent : "+text);  //NIIT Ltd
        Thread.sleep(1000);

//  Child / copil - Selects all children of the current node (One or many) - selecteaza toti copii noduil-locatorului curent
        List<WebElement> childs=driver.findElements(By.xpath("//a[contains(text(),'NIIT Ltd')]/ancestor::tr/child::td"));
        System.out.println("Number of child elements:"+childs.size());//5
        Thread.sleep(1000);

//  Ancestor/stramos - Selects all ancestors (parent, grandparent, etc.) - selecteaza toti stramosii nodul-locatorului curent
        text=driver.findElement(By.xpath("//a[contains(text(),'NIIT Ltd')]/ancestor::tr")).getText();
        System.out.println("Ancestor : "+text);
        Thread.sleep(1000);

//  Descendant / descendenti - Selects all descendants (children, grandchildren, etc.) of the current node - selecteaza toti
//  copii, nepotii noidului-locatorului curent
        List<WebElement> descendants=driver.findElements(By.xpath("//a[contains(text(),'NIIT Ltd')]/ancestor::tr/descendant::*"));
        System.out.println("Number of descendant nodes:"+descendants.size());
        Thread.sleep(1000);

//  Following / urmareste - Selects everything in the document after the closing tag of the current node - selecteaza totul din
//  document, dupa eticheta de inchidere a nodului-locatorului curent
        List<WebElement>followingnodes=driver.findElements(By.xpath("//a[contains(text(),'NIIT Ltd')]/ancestor::tr/following::tr"));
        System.out.println("Number of following nodes:"+followingnodes.size());
        Thread.sleep(1000);

//  Following-sibling urmareste-frati : Selects all siblings after the current node - selecteaza toti fratii dupa nodul-locatorul curent.
        List<WebElement> followingsiblings=driver.findElements(By.xpath("//a[contains(text(),'NIIT Ltd')]/ancestor::tr/following-sibling::tr"));
        System.out.println("Number of Following Siblings:"+followingsiblings.size());
        Thread.sleep(1000);

//  Preceding / Precedenta - Selects all nodes that appear before the current node in the document - selecteaza toate nodurile-toti
// locatorii care apar inaintea locatorului curent in documentr
        List<WebElement> precedings=driver.findElements(By.xpath("//a[contains(text(),'NIIT Ltd')]/ancestor::tr/preceding::tr"));
        System.out.println("Number of preceding nodes:"+precedings.size());
        Thread.sleep(1000);

//Preceding-sibling / Precedenta-frati - Selects all siblings before the current node - selecteaza toti fratii de dinaintea si de dupa
// nodul-locatorul curent
        List<WebElement> precedingsiblings=driver.findElements(By.xpath("//a[contains(text(),'NIIT Ltd')]/ancestor::tr/preceding-sibling::tr"));
        System.out.println("Number of preceding sibling nodes:"+precedingsiblings.size());


        Thread.sleep(2500);
        driver.quit();

    }

}
