package de.arag.functinal.programming.iq;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DisallowedUrlTest {

    private final DisallowedUrl disallowedUrl = DisallowedUrl.getDisallowedUrl();

    @Test
    void disallowedUrls() {
        //Given
        List<String> urls = List.of(
                "User-agent: /aaa",
                "Disallowed: /zzz",
                "Disallowed: /uuu",
                "Disallowed: /ccc",
                "Disallowed: /vvv",
                "Disallowed: /aaa",
                "Disallowed: /ttt",
                "Disallowed: /xxx",
                "Disallowed: /bbb",
                "Disallowed:/bbb",
                "User-agent: /aaa");

        //When
        var response = disallowedUrl.disallowedUrls(urls);

        //Then
        assertThat(response).isNotNull().hasSize(8);
    }
}