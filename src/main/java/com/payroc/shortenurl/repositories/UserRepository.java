package com.payroc.shortenurl.repositories;

import com.payroc.shortenurl.ApplicationException;
import com.payroc.shortenurl.domain.User;
import com.payroc.shortenurl.mappers.UserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(long id) throws ApplicationException {

        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        List<User> users =  jdbcTemplate.query("select user_id, user_name from users where user_id = :id", parameters, new UserMapper());

        if (users.size() > 1) {
            throw new ApplicationException("Found more than one user with the same ID!");
        }

        return !CollectionUtils.isEmpty(users) ? users.get(0) : null;
    }

    public int createUser(String name) {
        SqlParameterSource parameters = new MapSqlParameterSource("name", name);
        return jdbcTemplate.update("insert into users(user_name = :name)", parameters);
    }

    public int deleteUser(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        return jdbcTemplate.update("delete from users where user_id = :id", parameters);
    }

}