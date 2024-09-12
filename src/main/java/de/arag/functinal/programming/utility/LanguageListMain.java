package de.arag.functinal.programming.utility;

import de.arag.functinal.programming.model.dto.LanguageList;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LanguageListMain {
    public static void main(String[] args) {

        LanguageList languageList = new LanguageList();

        languageList.addLanguage("java");
        languageList.addLanguage("c#");
        languageList.addLanguage("basic");
        languageList.addLanguage("c++");

        log.info(languageList.toString());

        languageList.removeLanguage("c++");

        log.info(languageList.toString());

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
        log.info(arrayList.toString());
        //arrayList = new ArrayList<>();
    }

}
