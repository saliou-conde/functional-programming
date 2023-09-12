package de.arag.functinal.programming.utility;

import org.junit.jupiter.api.Test;

class UrlSanitizerTest {

    private UrlSanitizer sanitizer = new UrlSanitizer();

    @Test
    void sanitizedUrl() {
        //Given
        String input = "https://www.arag.de/";

        //When
        sanitizer.displayUrlCollision(input, 6, 100);
    }
}