package Clasa_Diverse.diverse_Clase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Clasa_coockie {

    public Clasa_coockie(WebDriver driver){ PageFactory.initElements(driver, this); }

// Apelam pagina de coockie
    @FindBy(xpath="/html/body/app-root/app-theme/div/div/app-notice/app-theme/div/div/app-home/div/div[1]/div/div[1]")
    private static WebElement ManageYourPrivacy;
    public static WebElement getClickAcceptAll() { return ManageYourPrivacy; }


}
