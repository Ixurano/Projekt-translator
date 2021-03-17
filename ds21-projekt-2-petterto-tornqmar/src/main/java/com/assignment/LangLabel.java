package com.assignment;

/**
 * Den här enum-klassen är ett föslag. Det är fritt fram
 * att använda den eller låta bli, eller att ändra på den
 * enligt eget behov.
 * <p>
 * Man kan förstås ha en enklare enum (utan name och konstruktor) med
 * bara DE, EN, ES... men på det här sättet kan man enkelt få språkets
 * namn utskrivet vid behov!
 */
public enum LangLabel {

    DE("tyska"),
    EN("engelska"),
    ES("spanska"),
    FI("finska"),
    FR("franska"),
    IT("italienska"),
    NO("norska"),
    UNKNOWN("okänt"),
    SV("svenska");

    private String name;

    // Specialkonstruktor som körs av själva enum-konstanten
    LangLabel(String name) {
        this.name = name;
    }

    // Getter för namnet som initialiserats i konstruktorn. T.ex:
    // LangLabel.SV.getName(); returnerar "svenska"
    public String getName() {
        return name;
    }
}
