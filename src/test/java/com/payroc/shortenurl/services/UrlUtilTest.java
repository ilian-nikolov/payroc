package com.payroc.shortenurl.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UrlUtilTest {

    @Test
    void shouldEncodeAndDecodeUrl() {
        int inputId = 1352860476;
        String shortURL = UrlEncodingUtil.encodeUrlId(inputId);
        int decodedId = UrlEncodingUtil.decodeShortUrl(shortURL);
        assertThat(decodedId).isEqualTo(inputId);
    }

}