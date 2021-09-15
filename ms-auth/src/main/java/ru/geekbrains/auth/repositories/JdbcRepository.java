package ru.geekbrains.auth.repositories;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import ru.geekbrains.auth.entities.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcRepository {

    private static final String USER_ID = "USER_TABLE.ID";
    private static final String USER_EMAIL = "USER_TABLE.email";
    private static final String USER_PASSWORD = "USER_TABLE.password";

    private static final String ROLE_ID = "ROLE_TABLE.id";
    private static final String ROLE_NAME = "ROLE_TABLE.name";


    private final NamedParameterJdbcTemplate template;
    private final String getQuery;

    JdbcRepository(DataSource dataSource, ResourceLoader resourceLoader) {
        template = new NamedParameterJdbcTemplate(dataSource);
        getQuery = readAsString(resourceLoader.getResource("classpath:sql/get.sql"));
    }

    public Optional<User> getByEmail(String email) {
        Map<String, Object> params = new HashMap<>();
        params.put(USER_EMAIL, email);
        try {
            return Optional.ofNullable(template.queryForObject(
                    getQuery,
                    params,
                    getMapper()
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<User> getMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong(USER_ID));
            user.setEmail(rs.getString(USER_EMAIL));
            user.setPassword(rs.getString(USER_PASSWORD));

            return user;
        };
    }

    public static String readAsString(Resource resource) {
        try {
            InputStream is = resource.getInputStream();

            String var2;
            try {
                var2 = readAsString(is);
            } catch (Throwable var5) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }

                throw var5;
            }

            if (is != null) {
                is.close();
            }

            return var2;
        } catch (IOException var6) {
            throw new IllegalStateException("Can't read file from resource " + resource, var6);
        }
    }

    public static String readAsString(InputStream inputStream) {
        try {
            return StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        } catch (IOException var2) {
            throw new IllegalStateException("Can't read input stream", var2);
        }
    }

}
