package com.assignment;

import java.util.*;

public class Language {
    private String content;
    private String contentWhiteSpace; // Content but with "whitespace"
    private LangLabel langLabel;

    // Enkel bokstavs hasmaps
    public HashMap<String, Integer> charCount = new HashMap<String, Integer>();
    public HashMap<String, Double> charDistribution = new HashMap<String, Double>();

    // för 3 bokstävs kombo
    public HashMap<String, Integer> charThreeCount = new HashMap<String, Integer>();
    public HashMap<String, Double> charThreeDistribution = new HashMap<String, Double>();

    // för första bokstaven i ett ord kombo
    public HashMap<String, Integer> charFirstLetterCount = new HashMap<String, Integer>();
    public HashMap<String, Double> charFirstLetterDistribution = new HashMap<String, Double>();

    //för varje object
    public Language(String content, LangLabel langLabel) {
        this.content = content.replaceAll("[^\\p{L}]", "").toLowerCase();
        this.contentWhiteSpace = content.replaceAll("[^\\p{L}]", " ").toLowerCase().replaceAll("\\s{2,}", " ").trim();
        this.langLabel = langLabel;
        calculateCharDistribution();
        calculateThreeCharDistribution();
        calculateFirstLetterDistribution();
    }

    //Metod för att % för enkla bokstäver
    private void calculateCharDistribution() {
        for (int i = 0; i < content.length(); i++) {
            // Räknar ihop alla bokstäver i texten
            String letter = Character.toString(content.charAt(i));
            charCount.put(letter, charCount.getOrDefault(letter, 0) + 1);
        }

        // gör uträkningen hur stor % vardera bokstav har av hela texten.
        for (String key : charCount.keySet()) {
            charDistribution.put(key, (double) charCount.getOrDefault(key, 0) / (double) content.length());
        }

    }

    //metod för tre boksätvers %
    private void calculateThreeCharDistribution() {
        for (int i = 0; i < content.length(); i++) {
            if (i < content.length() - 2) {
                String threeLetters = content.substring(i, i + 3);
                charThreeCount.put(threeLetters, charThreeCount.getOrDefault(threeLetters, 0) + 1);
            }
        }
        //Gör uträkningen för metoden får % värdet för språk/content lägden
        for (String key : charThreeCount.keySet()) {
            charThreeDistribution.put(key, (double) charThreeCount.getOrDefault(key, 0) / (double) content.length());
        }

    }

    //metod för enkel bostav i varje ords %
    private void calculateFirstLetterDistribution() {
        if (contentWhiteSpace.length() > 0) {
            String[] wordSplitted = contentWhiteSpace.split(" ");

            for (int i = 0; i < wordSplitted.length; i++) {
                String firstLetter = Character.toString(wordSplitted[i].charAt(0));
                charFirstLetterCount.put(firstLetter, charFirstLetterCount.getOrDefault(firstLetter, 0) + 1);
            }
            //Gör uträkningen för metoden får % värdet för språk/content lägden
            for (String key : charFirstLetterCount.keySet()) {
                // System.out.println(charFirstLetterCount.getOrDefault(key, 0));
                charFirstLetterDistribution.put(key, (double) charFirstLetterCount.getOrDefault(key, 0) / (double) wordSplitted.length);
            }

        }
    }
}
