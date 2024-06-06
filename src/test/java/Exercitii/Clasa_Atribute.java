package Exercitii;

// Exercitiul se face pe pagina https://testpages.herokuapp.com/styled/attributes-test.html , de verificat ca e valida

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Clasa_Atribute extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/attributes-test.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        System.out.println("Verificam pagina in timp real sa vedem daca programul ruleaza corect! \n");


//  Titlul acestuie exercitiu este - GASITI ATRIBUTELE DIN PAGINA
        WebElement title = driver.findElement(By.xpath("//div[@class='page-body']"));
        System.out.println(title.getText());
        Thread.sleep(500);


        WebElement explanation = driver.findElement(By.cssSelector(".explanation"));
        System.out.println(explanation.getText());
        Thread.sleep(500);

        WebElement subtitle = driver.findElement(By.cssSelector("body>div:nth-child(1)>h2"));
        System.out.println(subtitle.getText());
        Thread.sleep(500);

        WebElement inlineExplanation = driver.findElement(By.cssSelector("body>div:nth-child(1)>p:nth-child(5)"));
        System.out.println(inlineExplanation.getText());
        Thread.sleep(500);

        WebElement paragraph_3 = driver.findElement(By.xpath("//div[@class='page-body'] [h1]"));;
        System.out.println(paragraph_3.getText());
        Thread.sleep(500);



        System.out.println("");
        driver.findElement(By.cssSelector(".styled-click-button")).click();


        Thread.sleep(1000);
        driver.quit();

        System.out.println("Pagina a fost incarcata si descarcat cu succes!");

/*
    pentru linia 25 si 42 am folosit XPATH dar se poate utiliza si css.Selector - formulele de mai jos:
    P25-     WebElement title = driver.findElement(By.cssSelector("div[class='page-body'] h1"));
    P42-     WebElement paragraph_3 = driver.findElement(By.cssSelector("#domattributes"));

 */

    }
}


