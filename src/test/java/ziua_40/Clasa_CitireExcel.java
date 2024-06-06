package ziua_40;

//  Urmam calea precizata anterior:     File -> Workbook -> Sheets -> Rows -> Cells

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Clasa_CitireExcel {

    public static void main(String[] args) throws IOException {

//  Gasim prima data fisierul excel :
        FileInputStream fisier_excel = new FileInputStream("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\src\\test\\java\\ziua_40\\date_ziua40\\data_ziua40.xlsx");

//  Putem sa utilizam calea scurta si sa punem linia:
//        FileInputStream fisier_excel = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\ziua_32\\date_ziua32\\data_ziua32.xlsx");

//  Trebuie sa capturam Workbook-ul din fisierul excel- aceste clase vin APACHI POI:
        XSSFWorkbook workbook = new XSSFWorkbook(fisier_excel);

//  Trebuie sa capturam Sheet-ul din Workbook-ul al fisierului excel:
        XSSFSheet sheet = workbook.getSheet("Sheet1");          // este varianta 1
//      XSSFSheet sheet = workbook.getSheetAt(0);                     // este varianta 2 - unde 0 este primul sheet

//  Trebuie sa capturam Rows din Sheet-ul din Workbook-ul al fisierului excel:
//  Acum o sa scriem un loop pentru Rows si unul pentru CXolumns - adica pentru identificarea celulelor
        int total_randuri = sheet.getLastRowNum();

        int total_celule = sheet.getRow(1).getLastCellNum();

//  Dupa ce facem captura de randuri si coloane-celule le tiparim ca sa vedem ce valori avem:

        System.out.println("Numar de randuri: " + total_randuri);   //  avem 6 randuri in tabel - inclusiv capul acestuia
        System.out.println("Numar de celule: " + total_celule);     //  avem 4 coloane in tabel

/*
ATENTIE:
    numarul de randuri este contorizat de la ZERO la 5 = 6 randuri, numarul de coloane este contorizat de la 1 la 4 = 4

    Trebuie sa citim numarul de linii si coloane care au fost gasite:
    Citirea se face pe rand si se citesc celulele de la stanga la dreapta pana se termina randul
    Se trece la randul urmator si se citesc celulelel de la stanga la dreapta. Si asa pana la final.
    Deci trebuie sa parcurgem toate celulele randului UNU si api trecem la randul urmator.
    Dupa asta trebuie sa facem camptura celulei in care am fost si verificat valoarea.
*/

        for (int r=0; r<=total_randuri; r++)
        {
//  Aici citim toate valorile de pe randul respectiv, deci selectam rand cu rand
            XSSFRow currentRow = sheet.getRow(r);

            for ( int c = 0; c<total_celule; c++)
            {
//  Aici citim toate valorile coloana-celula respectiv, deci selectam celula cu celula de la stanga la dreapta
                XSSFCell celula = currentRow.getCell(c);
                String valoare = celula.toString();

//  Avem varianta simplificata unde nu e apelata functia XSSFCell :
//              String valoare = currentRow.getCell(c).toString();

                System.out.println(valoare);
            }
        }
//  Dupa finalizarea task-urilor se inchide Workbook-ul si Fisierul Excel:
        workbook.close();
        fisier_excel.close();

/*  ATENTIE:
        ERROR StatusLogger Log4j2 could not find a logging implementation

    Primim aceasta eroare la rulare pentru ca nu avem o implementare corecta a APACHE POI
    In clasele urmatoare o sa rezolvam si aceasta problema.
*/


    }
}
