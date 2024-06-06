package ziua_31;// Aceasta clasa trateaza problema apelarii, afisarii si inspectiei dropdown-urilor

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class Clasa_DropDownTag extends DriverManager {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

//  Folosim un IMPLICIT wait de 10 secunde care trebuie sa astepte pana se incarca pagina cu datele specificate:
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
        driver.manage().window().maximize();

        driver.get("https://phppot.com/demo/");
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
/*  In cazul DropDown list exista:
    - cu eticheta/tag selectata în DOM
    - fara eticheta/tag selectata în DOM (input/div) - Bootstrap dropdown
    - cu AutoSuggested drop down (dinamic, random)

Pentru DropDown avem clasa speciala "Select class"
*/

//  Dupa ce intram pe pagina apasam butonul DEMO
        driver.findElement(By.className("demo-download-icon")).click();
        Thread.sleep(500);
//  In casuta de Search inseram textul pe care-l cautam
        driver.findElement(By.className("search")).sendKeys("Load Dependent Dropdown on Multi-Select using PHP and jQuery");
        Thread.sleep(500);
//  Apasam pe butonul de cautare
        driver.findElement(By.className("search-button")).click();
        Thread.sleep(500);
//  In noua pagina deschisa selectam pagina cautata:
        driver.findElement(By.xpath("//*[@id=\"tutorial\"]/div[1]/h3/a")).click();
        Thread.sleep(500);
//  Apasam butonul de VIEW DEMO :
        driver.findElement(By.xpath("//*[@id=\"tutorial\"]/p[3]/a")).click();
        Thread.sleep(500);
//  Pentru tara de origine avem un dropdown list pe care trebuie sa-l accesam, deci intai in realizam ca o lista
        WebElement dropDownCountry = driver.findElement(By.xpath("//option[normalize-space()=\"Select Country\"]"));

//  Pentru ca lista nu e dinamica o sa selectam direct o tara si partea de cautare o comenatam:
//        Select dropDownlist =  new Select(dropDownCountry);
//            // Deci o selectam o optiunne din dropDownlist
//        dropDownlist.selectByVisibleText("China");
//        Thread.sleep(500);

        WebElement dropDown_country = driver.findElement(By.xpath("//option[@value=\"2\"]"));
        dropDown_country.click();

        WebElement dropDown_state = driver.findElement(By.xpath("//option[@value=\"0\"]"));
        dropDown_state.click();

//  transformam parte citita in text ca sa putem s-o tiparim:

        String text1 = dropDown_country.getText();
        String text2 = dropDown_state.getText();
        System.out.println("Suntem in provincia " + text2 + " din " + text1);

        Thread.sleep(2500);
        driver.quit();
    }

/*
In cazul unei liste drop down dinamice o sa avem varianta de tiparire a eleemnteleor din lista

    WebElement dropDown_country = driver.findElement(By.xpath(" aici se scrie xpath-ul de care avem nevoie"));
    Select dropDown_list = new Select(dropDown_country);

//  Din lista selectam dupa nume - Romania
    dropDown_list.selectByVisisbleText("Romania");
//  Sau varianat sa utilizam nuamrul asignat acesteia in lista - 10
    dropDown_list.selectByValue("10");

//  Cautam toate elementele din dropDown
    List<WebElement> tari = dropDown_list.getOptions();
    System.out.println("Numarul total de tari " + tari.size());

//  tiparim tarile la consola
- varianta 1:
    for (int i = 0; i< tati.size(); i++)
    {
        System.out.println(tari.get(i).getText());
    }

- variant 2:
    for(WebElement op:tari)
    {
        System.out.println(tari.getText());
    }



 */



}
