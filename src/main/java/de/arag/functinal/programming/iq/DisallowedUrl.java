package de.arag.functinal.programming.iq;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class DisallowedUrl {

    private static DisallowedUrl disallowedUrl;
    private DisallowedUrl(){
    }

    public static DisallowedUrl getDisallowedUrl() {
        return disallowedUrl == null ? new DisallowedUrl() : disallowedUrl;
    }

    public List<String> disallowedUrls(List<String> urls) {

        List<String> list = new java.util.ArrayList<>(urls.stream()
                .filter(el -> el.startsWith("Disallowed: "))
                .toList()
                .stream()
                .map(el -> el.replace("Disallowed: ", ""))
                .toList());
        list.sort(Comparator.comparing(Function.identity()));
        log.info("{}", list);
        return list;
    }
}
