package ziua_41;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Clasa_FDCalculator {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
		Thread.sleep(3000);

		String filePath=System.getProperty("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\src\\test\\java\\ziua_41\\Fisiere_41\\caldata.xlsx");
		Thread.sleep(3000);

		int rows=Clasa_ExcelUtilitati.getRowCount(filePath, "Sheet1");
		Thread.sleep(3000);

		for(int i=1;i<=rows;i++)
		{
//	1. read data from excel, heet
			String pric=Clasa_ExcelUtilitati.getCellData(filePath, "Sheet1",i,0);
			String rateofinterest=Clasa_ExcelUtilitati.getCellData(filePath, "Sheet1",i,1);
			String per1=Clasa_ExcelUtilitati.getCellData(filePath, "Sheet1",i,2);
			String per2=Clasa_ExcelUtilitati.getCellData(filePath, "Sheet1",i,3);
			String fre=Clasa_ExcelUtilitati.getCellData(filePath, "Sheet1",i,4);
			String exp_mvalue=Clasa_ExcelUtilitati.getCellData(filePath, "Sheet1",i,5);
			
			//2) pass above data into application
			driver.findElement(By.xpath("//input[@id='principal']")).sendKeys(pric);
			driver.findElement(By.xpath("//input[@id='interest']")).sendKeys(rateofinterest);
			driver.findElement(By.xpath("//input[@id='tenure']")).sendKeys(per1);
			
			Select perdrp=new Select(driver.findElement(By.xpath("//select[@id='tenurePeriod']")));
			perdrp.selectByVisibleText(per2);
			
			
			Select fredrp=new Select(driver.findElement(By.xpath("//select[@id='frequency']")));
			fredrp.selectByVisibleText(fre);
			
			driver.findElement(By.xpath("//div[@class='cal_div']//a[1]")).click(); //clicked on calculate
			
			
//	validation of
			String act_mvalue=driver.findElement(By.xpath("//span[@id='resp_matval']//strong")).getText();

			System.out.println("Se tipareste valoarea captuarata" + act_mvalue);

			if(Double.parseDouble(exp_mvalue)==Double.parseDouble(act_mvalue))
			{
				System.out.println("Test passed");
				Clasa_ExcelUtilitati.setCellData(filePath, "Sheet1",i,7,"Passed");
				Clasa_ExcelUtilitati.fillGreenColor(filePath, "Sheet1",i,7);
			}
			else
			{
				System.out.println("Test failed");
				Clasa_ExcelUtilitati.setCellData(filePath, "Sheet1",i,7,"Failed");
				Clasa_ExcelUtilitati.fillRedColor(filePath, "Sheet1",i,7);
			}
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//img[@class='PL5']")).click(); // clicked on clear button
			
		} //ending of for loop
		
		driver.quit();
	}

}
