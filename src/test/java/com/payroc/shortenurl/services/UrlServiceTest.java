package com.payroc.shortenurl.services;

import com.payroc.shortenurl.ApplicationException;
import com.payroc.shortenurl.domain.Url;
import com.payroc.shortenurl.repositories.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.payroc.shortenurl.services.UrlService.LOCALHOST_8080;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UrlServiceTest {

    private UrlRepository urlRepositoryMock = mock(UrlRepository.class);

    private UrlService urlService;
    private Url url;

    @BeforeEach
    void setUp() {
        url = new Url();
        url.setFullUrl("http://www.payroc.com");
        url.setCreationDate(new Date());
        urlService = new UrlService(urlRepositoryMock);
    }

    @Test
    void shouldShortenUrl() throws Exception {
        final int idToShorten = 5;
        when(urlRepositoryMock.createUrl(eq(url))).thenReturn(idToShorten);
        when(urlRepositoryMock.updateWithShortUrl(eq(url))).thenReturn(1);
        urlService.shortenUrl(url);
        assertThat(url.getId()).isEqualTo(idToShorten);
        assertThat(url.getShortUrl()).isEqualTo(LOCALHOST_8080 + UrlEncodingUtil.encodeUrlId(idToShorten));
    }

    @Test
    void shouldThrowErrorWhenFailedToCreateUrl() {
        when(urlRepositoryMock.createUrl(eq(url))).thenReturn(0);
        when(urlRepositoryMock.updateWithShortUrl(eq(url))).thenReturn(1);
        ApplicationException exception = assertThrows(ApplicationException.class, () -> urlService.shortenUrl(url));
        assertThat(exception.getMessage()).isEqualTo("Error saving URL=http://www.payroc.com");
    }

    @Test
    void shouldThrowErrorWhenFailedToShortenUrl() {
        when(urlRepositoryMock.createUrl(eq(url))).thenReturn(1);
        when(urlRepositoryMock.updateWithShortUrl(eq(url))).thenReturn(2);
        ApplicationException exception = assertThrows(ApplicationException.class, () -> urlService.shortenUrl(url));
        assertThat(exception.getMessage()).isEqualTo("Error shortening URL=http://www.payroc.com");
    }

    @Test
    void test() {
        int result = 10 / 62;
        String a = UrlEncodingUtil.encodeUrlId(3);
        System.out.println(a);
    }

}