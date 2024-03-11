package ziua_28;

import drivers.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Clasa_Tema_CumparamBilet extends DriverManager {
         static void selectDate(WebDriver driver, WebElement datePicker, String date, String month, String year)
        {
            datePicker.click();

            Select drp=new Select(driver.findElement(By.xpath("//select[@aria-label='Select month']")));
            drp.selectByVisibleText(month);

            drp = new Select(driver.findElement(By.xpath("//select[@aria-label='Select year']")));
            drp.selectByVisibleText(year);

            List<WebElement> dates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tr/td"));

            for(WebElement dt:dates)
            {
                if(dt.getText().equals(date))
                {
                    dt.click();
                    break;
                }
            }
        }


//  Codul pt rulare

    public static void main(String[] args) throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
        WebDriver driver =new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.dummyticket.com/dummy-ticket-for-visa-application/");
        driver.manage().window().maximize();

        driver.findElement(By.id("product_550")).click(); // 990
        driver.findElement(By.id("travname")).sendKeys("John"); //First Given name
        driver.findElement(By.id("travlastname")).sendKeys("Kenedy"); //Last Surname (optional)
        driver.findElement(By.id("order_comments")).sendKeys("Testing"); //order_comments

//  Selectam DOB
        WebElement dobDatePicker=driver.findElement(By.xpath("//input[@id='dob']"));
        selectDate(driver, dobDatePicker,"5","Jul","1985"); //date, month,year   //User defined method to select DOB.

        driver.findElement(By.id("sex_2")).click(); //Sex
        driver.findElement(By.id("traveltype_2")).click();  // Round Trip
        driver.findElement(By.name("fromcity")).sendKeys("Hyderabad"); //From city / Origin
        driver.findElement(By.name("tocity")).sendKeys("Delhi"); //To city. /Destination

        WebElement depDateDatePicker=driver.findElement(By.xpath("//input[@id='departon']"));
        selectDate(driver, depDateDatePicker, "30","Oct","2024"); //Departure date  DD/MM/YYYY

        WebElement returnDateDatePicker=driver.findElement(By.xpath("//input[@id='returndate']"));
        selectDate(driver, returnDateDatePicker, "15","Nov","2024"); //return date  DD/MM/YYYY


//  Optiuni de livrare:

        //What is the dummy ticket for...? //Boostrap DropDown
        driver.findElement(By.xpath("//span[@id='select2-reasondummy-container']")).click();
        driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("Visa extension"+ Keys.ENTER);

        //How will you like to receive the dummy ticket (optional)
        driver.findElement(By.xpath("//input[@id='deliverymethod_2']")).click(); //WhatsApp


//Billing Details:

        driver.findElement(By.name("billname")).sendKeys("John");
        driver.findElement(By.name("billing_phone")).sendKeys("703-789-5417");
        driver.findElement(By.name("billing_email")).sendKeys("abcd@gmail.com");

        driver.findElement(By.xpath("//span[@id='select2-billing_country-container']")).click(); //Boostrap DropDown
        driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("India"+Keys.ENTER); //Country

//  Adresa - strada si numarul
        driver.findElement(By.name("billing_address_1")).sendKeys("13646 ABC");
        driver.findElement(By.name("billing_address_2")).sendKeys("ABC");
        driver.findElement(By.name("billing_city")).sendKeys("HYD");

//
//            driver.findElement(By.xpath("//span[@id='select2-billing_state-container']")).click();         //Boostrap DropDown
//            driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys("Telangana"+Keys.ENTER); //state

        driver.findElement(By.name("billing_state")).sendKeys("Taladega");  // Statul

        driver.findElement(By.id("billing_postcode")).sendKeys("500090");   // codul ZI

        driver.findElement(By.xpath("//button[@id='place_order']")).click(); //Place Order

        Thread.sleep(5000);

        System.out.println(driver.getTitle());// target page title after click on "Place Order"

        if(driver.getTitle().equals("Payment Page"))
        {
            System.out.println("Test Passed");
        }
        else
        {
            System.out.println("Test Failed");
        }

    Thread.sleep(5000);
    driver.quit();
        }

 }
