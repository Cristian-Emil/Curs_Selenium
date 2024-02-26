import drivers.DriverManager;
// import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Set;

public class Clasa_AlertInputInbox extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();


//---------------------------- OPTIUNEA ACEASTA SE ANULEAZA ... deci se comenteaza ----------------------------------
//        driver.get("https://the-internet.herokuapp.com/basic_auth");

        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Thread.sleep(1000);

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        Thread.sleep(1000);

        String text = driver.findElement(By.xpath("//p[contains(text(),'Congratulations!')]")).getText();

        if(text.contains("Congratulations"))
        {
            System.out.println("Succesful login");
        }
        else
        {
            System.out.println("Login failed");
        }

        Thread.sleep(5000);
        driver.quit();

    }

}
