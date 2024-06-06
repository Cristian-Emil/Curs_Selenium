package ziua_35;

public class Clasa_ActionsClass {

    public static void main(String[] args) {
        System.out.println("Daca ai citit tot ce este mai jos atunci ai inteles cum functioneaza actiunile mouse-ului");

/*
    Actions - este o clasa furnizata de Selenium webdrivrer.
    build() - creaza o actiune, genereaza o actiune
    perform() - poate finaliza / completa / face actiune

    Acestea se mai pot scrie si astfel:
    build.perform() - "build" creaza actiune si "perform" o executa
    perform() - "perform" simplu doar o executa

    Avem urmatoarele element privind actiunile ce se pot realiza cu mouse-ul

    Mouse hover - moveToElement(element) - plasarea sagetii pe un element
    Right click - contextClick(Element) - click dreapta ca sa afisam ceva
    Double click - doubleClick(Element) - dublu click care se da la fel ca un ENTER (dar nu e eneter)
    Drag and drop - dragAndDrop() - trage si da drumu este pentru a aduce un element intr-un anumit camp
    Slider element - dragAndDropBy(Element,x,y) - element care poate sa culiseze pe o bara sau pe linie
 */

/*
    Diferenta dintre - getText() si getAttribute()

    daca avem <input id='abc'> testing </ input> testing - inner text
    comanda findElement(Loc).getText() --- va returna --- testing
    comanda findElement(Loc).getAttribute() --- va returna --- nothing

    daca avem <input id='abc' value=testing / > testing - noinner text
    comanda findElement(Loc).getText() --- va returna --- nothing
    comanda findElement(Loc).getAttribute() --- va returna --- testing

    getText returneaza textul interior al elementului (inner text of element)

    getAttribute("value:) --- testing
    getAttribute("id") ------ abc

    deci getText va returna doar textul din interiorul elementului in timp ce getAttribute poate retruna textul sau
    valoarea din interiorul elementului, in functie de cum este adresat.
 */


    }
}
