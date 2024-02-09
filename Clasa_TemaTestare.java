import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Clasa_TemaTestare {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/CRISTIAN ZIDARESCU/IdeaProjects/- driver Selenium-Maven/ChromeDriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

//  Apelam URL-ul de care avem nevoie folosinf functia GET. URL-ul nsotru este "https://opensource-demo.orangehrmlive.com/"
        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
        Thread.sleep(1000);


//  Maximizam pagina cat tot ecranul pt o vizibilitate buna si folosim linia de comanda:
        driver.manage().window().maximize();


//  Inseram username si parola dar pentru ca valorile sunt deja inserate trebuie mai intia sa curatam casutele :

        WebElement userName = driver.findElement(By.xpath("//*[@id=\"Email\"]"));
        userName.clear();

        WebElement pass = driver.findElement(By.xpath("//*[@id=\"Password\"]"));
        pass.clear();

//  si apoi inseram noile valori pe care dorim sa le utilizam

        userName.sendKeys("admin@yourstore.com");
        pass.sendKeys("admin");


//  Pentru a da click pe butonul de login o sa folosim varinta lunga de comanda , varianta scurta este comentata:
//        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")).click();

        WebElement login_page = driver.findElement(By.cssSelector("body > div.master-wrapper-page > div > div > div > div > div.page-body > div.customer-blocks > div > form > div.buttons > button"));
        login_page.click();
        Thread.sleep(500);

//  Cautam partea de "NopCommerce News" din Dashboard pe care o verificam:
        WebElement comment1= driver.findElement(By.xpath("//*[@id=\"nopcommerce-news-box\"]/div[1]/div[1]"));

        String text_cautat = comment1.getText();
        String text_gasit = "NopCommerce News";

        if(text_cautat.equals(text_gasit)){
            System.out.println("Testul 'NopCommerce News' a trecut");
        }
        else {
            System.out.println("Nu a fost gasit elementul 1 cautat");
        }

//  Cautam partea de "Common statistics" din Dashboard pe care o verificam:
        String titlu1_cautat = null;
        try {
            titlu1_cautat = driver.findElement(By.xpath("//*[@id=\"nopcommerce-common-statistics-card\"]/div[1]/div[1]")).getText();
        }
        catch(NoSuchElementException e) {
            titlu1_cautat = "";
        }

        String titlu1_gasit = "Common statistics";

        if(titlu1_cautat.equals(titlu1_gasit)){
            System.out.println("Testul 'Common statistics' a trecut");
        }
        else {
            System.out.println("Nu a fost gasit elementul 2 cautat");
        }

        Thread.sleep(1500);
        driver.quit();
    }

/*  Daca dorim sa inseram alte credentiale pentr USERNAME si PAROLA trebuie sa curatam intia valorile existente:

    WebElement text_mail_box= driver.findElement(By.xpath("//*[@id=\"nopcommerce-news-box\"]/div[1]/div[1]"));
    text_mail_boc.clear();


*/



}
