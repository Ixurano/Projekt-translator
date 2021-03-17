package com.assignment;

import java.util.*;

public class LanguageStats {
    HashMap<LangLabel, Language> languages = new HashMap<LangLabel, Language>();

    //Lägger till "språket" userInput samt de existerande språken i filerna
    public void addLanguage(String userInput) {
        // loopar igenom alla enum och lägger till Languages i hashmappen
        for (LangLabel lang : LangLabel.values()) {
            if (lang == LangLabel.UNKNOWN) {
                // skapar en unknown language.
                languages.put(lang, new Language(userInput, LangLabel.UNKNOWN));
            } else {
                languages.put(
                        lang,
                        new Language(FileUtils.readTextFile("assets/lang-samples/" + lang + ".txt"), lang)
                );
            }
        }
    }

    // Gissar språket på basen av en bokstav
    public HashMap<LangLabel, Double> guessLanguage() {
        HashMap<LangLabel, Double> totalDifference = new HashMap<LangLabel, Double>();
        //Loopar igenom varje språk förutom för LangLabel.unknown
        for (Map.Entry<LangLabel, Language> entry : languages.entrySet()) {
            if (!(entry.getKey() == LangLabel.UNKNOWN)) {
                double difference = 0.0;
                // for-loop som gör uträkningen för difference på de aktuella språket
                for (String key : languages.get(entry.getKey()).charDistribution.keySet()) {
                    double assetsChar = languages.get(entry.getKey()).charDistribution.get(key);
                    double unknownChar = languages.get(LangLabel.UNKNOWN).charDistribution.getOrDefault(key, 0.0);
                    difference = difference + Math.abs(unknownChar - assetsChar);

                }

                totalDifference.put(entry.getKey(), difference);
            }
        }

        return totalDifference;
    }

    // Gissar språk enligt three characters
    public HashMap<LangLabel, Double> guessLanguageByThree() {
        HashMap<LangLabel, Double> totalDifference = new HashMap<LangLabel, Double>();

        for (Map.Entry<LangLabel, Language> entry : languages.entrySet()) {
            if (!(entry.getKey() == LangLabel.UNKNOWN)) {
                double difference = 1.0;
                for (String key : languages.get(entry.getKey()).charThreeDistribution.keySet()) {
                    double assetsChar = languages.get(entry.getKey()).charThreeDistribution.get(key);
                    double unknownChar = languages.get(LangLabel.UNKNOWN).charThreeDistribution.getOrDefault(key, 0.0);

                    if (unknownChar != 0) {
                        difference = difference - Math.abs(assetsChar);
                    }

                }

                totalDifference.put(entry.getKey(), difference);
            }
        }


        return totalDifference;
    }

    // Gissar språk enligt första karaktären i varje ord
    public HashMap<LangLabel, Double> guessLanguageByFirstLetter() {
        HashMap<LangLabel, Double> totalDifference = new HashMap<LangLabel, Double>();

        for (Map.Entry<LangLabel, Language> entry : languages.entrySet()) {
            if (!(entry.getKey() == LangLabel.UNKNOWN)) {
                double difference = 0.0;

                for (String key : languages.get(entry.getKey()).charFirstLetterDistribution.keySet()) {
                    double assetsChar = languages.get(entry.getKey()).charFirstLetterDistribution.get(key);
                    double unknownChar = languages.get(LangLabel.UNKNOWN).charFirstLetterDistribution.getOrDefault(key, 0.0);
                    difference = difference + Math.abs(unknownChar - assetsChar);

                }

                totalDifference.put(entry.getKey(), difference);

            }
        }
        return totalDifference;
    }

    //räknar ut medeltalet för de 3 olika metoderna
    public HashMap<LangLabel, Double> calculateMean() {
        HashMap<LangLabel, Double> totalDifference = new HashMap<LangLabel, Double>();

        for (Map.Entry<LangLabel, Double> entry : guessLanguage().entrySet()) {
            double first = entry.getValue();
            double second = guessLanguageByThree().get(entry.getKey());
            double third = guessLanguageByFirstLetter().get(entry.getKey());
            totalDifference.put(entry.getKey(), (first + second + third) / 3);
        }
        return totalDifference;
    }

    // https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
    // function to sort hashmap by values 
    public HashMap<LangLabel, Double> sortByValue(HashMap<LangLabel, Double> hm) {
        // Create a list from elements of HashMap 
        List<Map.Entry<LangLabel, Double>> list = new LinkedList<Map.Entry<LangLabel, Double>>(hm.entrySet());

        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<LangLabel, Double>>() {
            public int compare(Map.Entry<LangLabel, Double> o1, Map.Entry<LangLabel, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<LangLabel, Double> sorted = new LinkedHashMap<LangLabel, Double>();

        for (Map.Entry<LangLabel, Double> key : list) {
            sorted.put(key.getKey(), key.getValue());
        }

        return sorted;
    }

    //metod för att sortera(ranka) de olika svaren.
    public ArrayList<String> tabelMethod() {

        Map<LangLabel, Double> sorted = sortByValue(calculateMean());
        //lista för att printa ut rätt i UI klassen
        ArrayList<String> rankadLista = new ArrayList<>();
        int i = 1;
        // print the sorted hashmap 
        for (Map.Entry<LangLabel, Double> entry : sorted.entrySet()) {

            rankadLista.add("" + entry.getKey() + "   | " + String.format("%.2f", entry.getValue()) + "    | " + String.format("%.2f", guessLanguage().get(entry.getKey())) + "    | " + String.format("%.2f", guessLanguageByThree().get(entry.getKey())) + "   |  " + String.format("%.2f", guessLanguageByFirstLetter().get(entry.getKey())) + "   |   " + i);

            i = i + 1;

        }
        return rankadLista;
    }
}
