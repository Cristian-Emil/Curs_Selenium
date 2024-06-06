package ziua_40;
/*  Aici tratam problema datelor care se scriu in cadrul unei pagini EXCEL. Aceasta se face cu APACHE POI
    Apache api este un API  ustilizat pentru ms documents - in special excel. Citeste date din fisiere excel
    Poate sa scrie si citeasca date din cadrul unui fisier excel.

    Pentru a avea instalata conexiunea cu APACHE POI se merge in "mvn repository" si se cauta apache poi
    Se copiaza si instaleaza:
    Apache POI Common
    Apache POI API Based On OPC and OOXML Schemas
    in cadrul fisierului POM.XML

    Acum trebuie sa intelegem structura EXCEL SHEET-ului, el este un file list si contine:
    Workbooks - care contine mai multe sheet-uri
    Shhets - contin la rsandul lor randuri si coloane

    Excel File ---> Workbook ---> Sheets ---> Rows ---> Cells
    In CELLS (celula) sunt scrise valorile de care avem nevoie
    Pentru a ajunge la valoare se merge din pas in pas : Excel -> Workbook -> Sheet -> Row -> Cell
    Pentru fiecare din ele exista o clasa dedicata care contin diferite impuneri / conditii

    Cu clasele care vin din din JAVA :
    FileInputStream
    FileOutputStream
    putem sa intram si sa iesim din diverse file , aici in mod special pentru EXCEL FILE

    XSSFWorkbook    --->    este dedicat pentru Workbook
    XXSSFSheet      --->    este dedicat pentru Sheet
    XXSSFRow        --->    este dedicat pentru Row
    XXSSFCell       --->    este dedicat pentru Cell

    Am creat un fisier "date_ziua32" in cadrul clasei "ziua32". In interior am adaugat cele 2 fisiere excel.
    In clasa "Clasa_citireExcel" o sa utilizam aceste fisiere pt citire
    In clasa "Clasa_scriereExcel" o sa utilizam aceste fisiere pt scriere.
*/

import drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Clasa_DataDrivenTest extends DriverManager {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
        Thread.sleep(1000);

//  partea de focalizare pe pagina ------ nu este obligatorie dar se recomanda ca sa nu pierdem accesul pe pagina
        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

//  incepem sa scriem codul in clasaele urmatoare


    Thread.sleep(5000);
    driver.close();
    driver.quit();
    }
}
