package ziua_40;

/*  ATENTIE : secventa pt Excel File ---> Workbook ---> Sheets ---> Rows ---> Cells in mod normal
    Cand dorim sa cream un fisier in care trebuie sa scrie parcurgem pasii in pordinea data.

*/

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Clasa_ScriereExcel {

    public static void main(String[] args) throws IOException {

        FileOutputStream fila = new FileOutputStream("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\src\\test\\java\\ziua_40\\date_ziua40\\fila_scris.xlsx");
//  Avem varianta scurta ( dar care nu o utilizam acum ) :
//        FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\sr\tes\java\\ziua_32\\date_ziua3\\fila_scris.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet();

//  Cele 2 linii de mai jos le putem scrie ca un singur rand:
//        XSSFCell celula = sheet.createRow(0).createCell(1);
//        celula.setCellValue("Bun venit");

//  Folosim acesta metoda ca sa scrie mai multe valori in diverse casute:
//        sheet.createRow(0).createCell(1).setCellValue("Bun venit");
//        sheet.createRow(0).createCell(2).setCellValue("1 2 3");
//        sheet.createRow(0).createCell(3).setCellValue("x, y, z");
//
//        sheet.createRow(1).createCell(1).setCellValue("Ion");
//        sheet.createRow(1).createCell(2).setCellValue("Maria");
//        sheet.createRow(1).createCell(3).setCellValue("q, v, w");
//
//        sheet.createRow(2).createCell(1).setCellValue("Are tuica");
//        sheet.createRow(2).createCell(2).setCellValue("Are pere");
//        sheet.createRow(2).createCell(3).setCellValue("Am terminat");

//  Se observa ca avem doar ultima coloana completata din cauza ca nu facem sistemul iterativ.

//  Folosim metoda Scanner de introdcuere a datelor de la tastatura:
        Scanner scaneaza = new Scanner(System.in);

//   Pentru a elimina problema cu casutele care nu sunt completate facem iteratie pe randuri si celule:
        for (int r=0; r<3; r++)
        {
            XSSFRow rand_curent = sheet.createRow(r);

            for (int c=0; c<3; c++)
            {
//  daca utilizam linia de mai jos o sa avem aceeasi valoare in toate cele 9 casute.
//                rand_curent.createCell(c).setCellValue("Bun venit");

//  pentru a evita acesta problema o sa cerem sa se introduca de la tastatura cele 9 valori cu Scanner
                System.out.println("Introdceti valoare:" );
                String valoare = scaneaza.next();
                rand_curent.createCell(c).setCellValue(valoare);
            }

        }
//  Atasam valorile in workbook-ul dupa care le inchidem si le verificam in folderul nostru daca exista:
        workbook.write(fila);
        workbook.close();
        fila.close();

        System.out.println("Fisierul e scris");

    }
}
