package com.payroc.shortenurl.mappers;

import com.payroc.shortenurl.domain.Url;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UrlMapper implements RowMapper<Url>  {

    @Override
    public Url mapRow(ResultSet rs, int rowNum) throws SQLException {
        Url url = new Url();
        url.setId(rs.getInt("url_id"));
        url.setFullUrl(rs.getString("full_url"));
        url.setShortUrl(rs.getString("short_url"));
        url.setCreationDate(new Date(rs.getTimestamp("time_created").getTime()));
        return url;
    }
}
