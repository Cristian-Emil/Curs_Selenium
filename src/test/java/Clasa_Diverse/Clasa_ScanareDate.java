package Clasa_Diverse;

import java.util.Scanner;

public class Clasa_ScanareDate {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//  Introducem si tiparim un sir de caractere:
        System.out.print("Va rugam sa introduceti text pt citire = " + "\n");
//  Dupa introducerea textului se apasa ENTER

// Citim șirul de caractere introduse anterior
        String inputString = scanner.nextLine();
        System.out.println("Ați introdus: " + inputString);

//  Introducem si tiparim un numar intreg:
        System.out.print("Va rugam sa introduceti un nr intreg = " + "\n");
//  Dupa introducerea numarului se apasa ENTER

//  Citim un numarul întreg introdus anterior
        int inputInt = scanner.nextInt();
        System.out.println("Ați introdus : " + inputInt);

//  Introducem si tiparim un numar cu virgula:
        System.out.print("Va rugam sa introduceti un nr zecimal = " + "\n");
//  ATENTIE:    numarul zecimal se scrie cu "PUNCT" si nu cu virgula.
//  Dupa introducerea numarului zecimal se apasa ENTER

//  Citim un numarul cu virgula introdus anterior:
        double inputDouble = scanner.nextDouble();
        System.out.println("Ați introdus : " + inputDouble);

//  Închidem comanda pentru scanner si eliberam memoria/resursele:
        scanner.close();

    }
}
