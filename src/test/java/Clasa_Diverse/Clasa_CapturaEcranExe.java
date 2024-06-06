package Clasa_Diverse;

import drivers.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Clasa_CapturaEcranExe extends DriverManager {

    public static void main(String[] args) throws IOException, InterruptedException {

        WebDriver driver = new ChromeDriver();

//  Avem un implicit wait de 10 secunde pentru orice eventualitate
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((10)));
//  Maximizam ecranul cand deschidem pagina de browser / URL
        driver.manage().window().maximize();

//  Dam delete la toate Cookie-urile
        driver.manage().deleteAllCookies();

//  Punem un tump de asteptare de 2 secunde pt ca netul e teribil:
        Thread.sleep(2000);

//  Deschidem pagina de OrangeHRMsi punem un timp de asteptare pt ca nu incarca pagina corect
        driver.get("https://opensource-demo.orangehrmlive.com/");
        Thread.sleep(5000);

//  Facem o captura de ecran sau de pe ecran:
    File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen,
                new File("C:\\Users\\CRISTIAN ZIDARESCU\\IdeaProjects\\Project_with_Selenium\\src\\test\\java\\Clasa_Diverse\\CapturaEcran\\ecran1.jpeg"));

    Thread.sleep(5000);
    driver.close();
    driver.quit();
    }
}
