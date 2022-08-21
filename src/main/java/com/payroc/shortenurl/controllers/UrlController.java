package com.payroc.shortenurl.controllers;

import com.payroc.shortenurl.ApplicationException;
import com.payroc.shortenurl.domain.Url;
import com.payroc.shortenurl.services.UrlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class UrlController {

    private static final Logger logger = LogManager.getLogger(UrlController.class);

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/index")
    public String urlForm() {
        return "index";
    }

    @RequestMapping(value="/shorten", method = RequestMethod.POST)
    public String shortenUrl(@ModelAttribute Url url, Model model) {
        if (!StringUtils.hasText(url.getFullUrl())) {
            return "redirect:/";
        }
        try {
            urlService.shortenUrl(url);
        } catch (ApplicationException e) {
            logger.error("Error shortening URL={}", url.getFullUrl(), e);
        }
        model.addAttribute("url", url);
        return "result";
    }

    @RequestMapping(value="/{shortUrl}", method = RequestMethod.GET)
    public String forwardUrl(@PathVariable String shortUrl) {
        // Filtering out "favicon.ico", which for some reason gets sent if the short URL is blank
        if (StringUtils.hasText(shortUrl) && !shortUrl.startsWith("favicon")) {
            try {
                return "redirect:" + urlService.getFullUrl(shortUrl);
            } catch (ApplicationException e) {
                logger.error("Error retrieving full url for {}", shortUrl, e);
            }
        }
        return "index";
    }

}