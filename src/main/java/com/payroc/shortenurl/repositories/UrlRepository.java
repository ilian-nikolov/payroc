package com.payroc.shortenurl.repositories;

import com.payroc.shortenurl.ApplicationException;
import com.payroc.shortenurl.domain.Url;
import com.payroc.shortenurl.mappers.UrlMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UrlRepository {

    private static final String INSERT_URL = "insert into url(full_url, time_created) values (?, ?)";
    private static final String UPDATE_WITH_SHORT_URL = "update url set short_url = ? where url_id = ?";
    private static final String GET_FULL_URL = "select url_id, full_url, short_url, time_created from url where url_id = :id";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UrlRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public int createUrl(Url url) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_URL, new String[] {"url_id"});
            ps.setString(1, url.getFullUrl());
            ps.setTimestamp(2, new Timestamp(new Date().getTime()));
            return ps;
        }, keyHolder);

        return null != keyHolder.getKey() ? keyHolder.getKey().intValue() : 0;
    }

    public int updateWithShortUrl(Url url) {
        return namedParameterJdbcTemplate.getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE_WITH_SHORT_URL);
            ps.setString(1, url.getShortUrl());
            ps.setInt(2, url.getId());
            return ps;
        });
    }
    public Url findById(int urlId) throws ApplicationException {
        SqlParameterSource parameters = new MapSqlParameterSource("id", urlId);
        List<Url> urls = namedParameterJdbcTemplate.query(GET_FULL_URL, parameters, new UrlMapper());
        if (urls.size() > 1) {
            throw new ApplicationException("Found more than one url with the same ID!");
        }
        return !CollectionUtils.isEmpty(urls) ? urls.get(0) : null;
    }

}
