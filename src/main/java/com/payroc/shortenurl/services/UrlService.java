package com.payroc.shortenurl.services;

import com.payroc.shortenurl.ApplicationException;
import com.payroc.shortenurl.domain.Url;
import com.payroc.shortenurl.repositories.UrlRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * A service to create and shorten URLs.
 */
@Service
public class UrlService {

    private static final Logger logger = LogManager.getLogger(UrlService.class);
    protected static final String LOCALHOST_8080 = "http://localhost:8080/";
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public void shortenUrl(Url url) throws ApplicationException {
        int urlId = urlRepository.createUrl(url);
        if (0 == urlId) {
            throw new ApplicationException("Error saving URL=" + url.getFullUrl());
        } else {
            // Pass url_id to shortening util
            url.setId(urlId);
            url.setShortUrl(LOCALHOST_8080 + UrlEncodingUtil.encodeUrlId(urlId));
            int result = urlRepository.updateWithShortUrl(url);
            if (1 != result) {
                throw new ApplicationException("Error shortening URL=" + url.getFullUrl());
            }
        }

    }

    public String getFullUrl(String shortUrl) throws ApplicationException {
        int urlId = UrlEncodingUtil.decodeShortUrl(shortUrl);
        Url url = urlRepository.findById(urlId);

        // limited only to checks for http/https protocols
        if (!url.getFullUrl().startsWith("http")) {
            url.setFullUrl("http://" + url.getFullUrl());
        }

        return url.getFullUrl();
    }

}
