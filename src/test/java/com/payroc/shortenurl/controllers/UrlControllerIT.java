package com.payroc.shortenurl.controllers;

import com.payroc.shortenurl.domain.Url;
import com.payroc.shortenurl.services.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerIT {

    private static final String HTTP_WWW_BBC_CO_UK = "http://www.bbc.co.uk";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlService urlService;

    @Test
    void shouldLoadHomePageSuccessfully() throws Exception {
        mockMvc.perform(get("/index")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk());
    }

    @Test
    void shouldEncodeLongUrl() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/shorten")
                .contentType(MediaType.TEXT_HTML)
                .param("fullUrl", HTTP_WWW_BBC_CO_UK))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertThat(content).contains("<a class=\"l\" href=\"http://localhost:8080/b\">http://localhost:8080/b</a>");
    }

    @Test
    void shouldDecodeShortUrl() throws Exception {
        Url url = new Url();
        url.setFullUrl(HTTP_WWW_BBC_CO_UK);
        urlService.shortenUrl(url); // shorten and save to database first in order for the test to find it
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/b")
                        .contentType(MediaType.TEXT_HTML))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound())
                .andReturn();

        assertThat(result.getResponse().getHeader("Location")).isEqualTo(HTTP_WWW_BBC_CO_UK);
    }

}