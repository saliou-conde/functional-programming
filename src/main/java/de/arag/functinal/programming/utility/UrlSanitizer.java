package de.arag.functinal.programming.utility;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class UrlSanitizer {

    private Random random = new Random();
    private String input = "abcd";
    private List<String> sanitizedUrls = new ArrayList<>();

    public void displayUrlCollision(String url, int n, int len) {
        String sanitize = sanitizeUrl(url, n);
        sanitizedUrl(url, n, len);
        int i = 0;
        while (i < sanitizedUrls.size()) {
            if(sanitizedUrls.contains(sanitize)) {
                log.info("index: {} Contains: {}", i, sanitize);
            }
            sanitize = sanitizeUrl(url, n);
            i++;
        }
    }

    private void sanitizedUrl(String url, int n, int len) {

        for(int i = 0; i < len; i++) {
            sanitizedUrls.add(sanitizeUrl(url, n));
        }
    }

    private String sanitizeUrl(String url, int n) {
        StringBuilder sb = new StringBuilder();
        int index;
        sb.append(url);
        for(int i = 0; i < n; i++) {
            index = random.nextInt(input.length());;
            sb.append(input.charAt(index));
        }
        return sb.toString();
    }
}
