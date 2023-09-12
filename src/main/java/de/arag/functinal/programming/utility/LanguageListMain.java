package de.arag.functinal.programming.utility;

import de.arag.functinal.programming.model.dto.LanguageList;

import java.util.ArrayList;
import java.util.List;

public class LanguageListMain {
    public static void main(String[] args) {

        LanguageList languageList = new LanguageList();

        languageList.addLanguage("java");
        languageList.addLanguage("c#");
        languageList.addLanguage("basic");
        languageList.addLanguage("c++");

        System.out.println(languageList);

        languageList.removeLanguage("c++");

        System.out.println(languageList);

        double value = 10.0;
        byte castToByte = (byte) value;
        System.out.println(castToByte);
        int intVal = 1000;
        long longVal = intVal;
        Integer integerVal = intVal;
        Long longValue = longVal;
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(List.of("Hello", "Joh", "Doe"));
        arrayList.add("Test");
        System.out.println(arrayList);
        //arrayList = new ArrayList<>();
    }

}
