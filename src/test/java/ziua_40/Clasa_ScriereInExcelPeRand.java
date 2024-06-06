package ziua_40;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class Clasa_ScriereInExcelPeRand {

    public static void main(String[] args) throws IOException {

        FileOutputStream fila = new FileOutputStream("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\src\\test\\java\\ziua_40\\date_ziua40\\fila_scris.xlsx");
//  Avem varianta scurta ( dar care nu o utilizam acum ) :
//        FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"\\sr\tes\java\\ziua_32\\date_ziua3\\fila_scris.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet();

//  Cele 2 linii de mai jos le putem scrie ca un singur rand:
        XSSFCell celula = sheet.createRow(0).createCell(1);
        celula.setCellValue("Bun venit");

//  Folosim acesta metoda ca sa scrie mai multe valori in diverse casute:
        sheet.createRow(0).createCell(1).setCellValue("Bun venit");
        sheet.createRow(0).createCell(2).setCellValue("1 2 3");
        sheet.createRow(0).createCell(3).setCellValue("x, y, z");

        sheet.createRow(1).createCell(1).setCellValue("Ion");
        sheet.createRow(1).createCell(2).setCellValue("Maria");
        sheet.createRow(1).createCell(3).setCellValue("q, v, w");

        sheet.createRow(2).createCell(1).setCellValue("Are tuica");
        sheet.createRow(2).createCell(2).setCellValue("Are pere");
        sheet.createRow(2).createCell(3).setCellValue("Am terminat");

//  Se observa ca avem doar ultima coloana completata din cauza ca sistemul itereaza mereu de la ZERO.
//  Pentruasta o sa generam o noua clasa "Clasa_ScriereExcel" unde o sa comernsatm aceste linii si face sistemul iterativ

    }
}
