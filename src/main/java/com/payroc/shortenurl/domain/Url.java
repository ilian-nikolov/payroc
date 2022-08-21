package com.payroc.shortenurl.domain;

import java.util.Date;

public class Url {

    private int id;
    private String fullUrl;
    private String shortUrl;
    private Date creationDate;

    public Url() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Url{" +
                "fullUrl='" + fullUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", creationDate=" + creationDate +
                "}";
    }

}