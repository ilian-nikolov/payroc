package com.payroc.shortenurl.services;

public class UrlEncodingUtil {

    private static final String BASE_62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    static String encodeUrlId(int n) {

        StringBuilder shortUrl = new StringBuilder();
        // A base62 encoding
        while (n > 0) {
            shortUrl.append(BASE_62.charAt(n % 62));
            n = n / 62;
        }

        // Reverse shortURL to complete base conversion
        return shortUrl.reverse().toString();
    }

    static int decodeShortUrl(String shortUrl) {
        int decode = 0;
        // A base62 decoding
        for(int i = 0; i < shortUrl.length(); i++) {
            decode = decode * 62 + BASE_62.indexOf(shortUrl.charAt(i));
        }
        return decode;
    }

}