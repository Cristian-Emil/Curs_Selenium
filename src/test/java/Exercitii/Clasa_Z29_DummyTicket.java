package Exercitii;

import org.checkerframework.common.initializedfields.qual.EnsuresInitializedFields;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Clasa_Z29_DummyTicket {

//----- initializam metoda cu care selectam datele PICKER-ului "calatorului" si o folosim de cate ori avem nevoie----------------------------
    static void selectDate(WebDriver driver, WebElement datePicker, String date, String month, String year) {
        datePicker.click();

        Select drp = new Select(driver.findElement(By.xpath("//select[@aria-label='Select month']")));
        drp.selectByVisibleText(month);

        drp = new Select(driver.findElement(By.xpath("//select[@aria-label='Select year']")));
        drp.selectByVisibleText(year);

        List<WebElement> dates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tr//td"));
//      //*[@id="ui-datepicker-div"]/table
//      //table[@class='ui-datepicker-calendar']//tr//td

        for (WebElement dt:dates) {
            if (dt.getText().equals(date))
            {
                dt.click();
                break;
            }
        }
    }
//------------------------------------ Aici incepe codul de program ----------------------------------------------------

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://www.dummyticket.com/dummy-ticket-for-visa-application/");

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

        driver.findElement(By.id("product_550")).click();
        driver.findElement(By.id("travname")).sendKeys("Vasile");
        driver.findElement(By.id("travlastname")).sendKeys("Bubulinul");
        driver.findElement(By.id("order_comments")).sendKeys("Testing");

//  select DOB
        WebElement dobDatePicker = driver.findElement(By.xpath("//*[@id='dob']"));
        selectDate(driver, dobDatePicker, "5","Aug","2024");

        driver.findElement(By.id("sex_1")).click();
        driver.findElement(By.id("traveltype_2")).click();
        driver.findElement(By.id("fromcity")).sendKeys("Bucuresti");
        driver.findElement(By.id("tocity")).sendKeys("Timisoara");

        WebElement depDateDatePicker = driver.findElement(By.xpath("//input[@id='departon']"));
        selectDate(driver,depDateDatePicker,"23","Aug","2024");

        WebElement returnDateDatePicker = driver.findElement(By.xpath("//input[@id='returndate']"));
        selectDate(driver,returnDateDatePicker,"29","Aug","2024");

//  optiuni cum sa fie livrat biletul
        driver.findElement(By.xpath("//span[@id='select2-reasondummy-container']")).click();
        driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("Visa extension"+ Keys.ENTER);
        Thread.sleep(1500);

//  alege optiune de a primi biletuyl pw WhatsApp:
        driver.findElement(By.xpath("//input[@id='deliverymethod_2']")).click();
        Thread.sleep(1500);

//  informatii pentru factura - nume, judet, aria, tara, cod poostal, adresa, :
        driver.findElement(By.name("billname")).sendKeys("Vasile");
        driver.findElement(By.name("billing_phone")).sendKeys("0721234567");
        driver.findElement(By.name("billing_email")).sendKeys("cristiantestare@hotmail.com");
        Thread.sleep(1500);

        driver.findElement(By.xpath("//span[@id='select2-billing_country-container']")).click();
        driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("Romania"+ Keys.ENTER);
        Thread.sleep(1500);

        driver.findElement(By.name("billing_address_1")).sendKeys("1234567 BABA");
        driver.findElement(By.name("billing_address_2")).sendKeys("1234567 MOSU");
        driver.findElement(By.name("billing_city")).sendKeys("In Deal");
        Thread.sleep(1500);

        driver.findElement(By.xpath("//span[@id='select2-billing_state-container']")).click();
        driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("Ilfov"+ Keys.ENTER);
        Thread.sleep(1500);

        driver.findElement(By.id("billing_postcode")).sendKeys("7000");
        Thread.sleep(1500);

        driver.findElement(By.xpath("//button[@id='place_order']")).click();
//      //button[@id='place_order']
//      //*[@id="place_order"]

        Thread.sleep(1500);

        System.out.println(driver.getTitle());

        if(driver.getTitle().equals("Payment Page"))
        {
            System.out.println("Testul a trecut");
        }
        else
        {
            System.out.println("Testul a picat");
        }

//  ATENTIE: testul o sa pice pt ca nu se emite nici o hartie de confirmare plata.

    Thread.sleep(3500);
    driver.close();
    driver.quit();
    }
}

