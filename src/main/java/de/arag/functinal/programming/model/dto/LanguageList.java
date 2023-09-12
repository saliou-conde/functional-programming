package de.arag.functinal.programming.model.dto;

import java.util.ArrayList;
import java.util.List;

public class LanguageList {
    private List<Language> list;

    public LanguageList() {
        list = new ArrayList<>();
    }

    public void addLanguage(String value) {
        Language language = new Language(value);
        list.add(language);
    }

    public void removeLanguage(String value) {
        Language language = new Language(value);
        boolean contains = list.equals(language);
        if(contains) {
            list.remove(language);
        }
    }

    public String toString() {
        String result = "";
        for (Language language : list) {
            result = result + language.getName() + "\n";
        }
        return result;
    }
}
