package com.assignment;

import java.util.*;

public class UserInterface {

    public void userInterface() {
        Scanner scanner = new Scanner(System.in);
        LanguageStats languageStats = new LanguageStats();
        System.out.println("Skriv din text: ");
        String userInput = scanner.nextLine();
        languageStats.addLanguage(userInput);
        System.out.println("Se tabell för sannolikheten av vilket språk du skriver på ");
        String leftAlignFormat = "| %-5s    |%n";
        System.out.format("+-----------------+-------------------------------------+%n");
        System.out.format("|Språk|Kombinerat| Analys 1| Analy2 | analys 3|Rang    |%n");
        System.out.format("+-----------------+-------------------------------------+%n");
        for (int i = 0; i < 8; i++) {
            System.out.format(leftAlignFormat, languageStats.tabelMethod().get(i));
        }
        System.out.format("+-----------------+-------------------------------------+%n");

    }
}
