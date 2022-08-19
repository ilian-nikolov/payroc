package com.payroc.shortenurl.domain;

import java.util.Date;

public class Url {

    private long id;
    private String fullUrl;
    private String shortUrl;
    private String alias;
    private Date creationDate;
    private Date expirationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
