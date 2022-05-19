package com.example.service.user.infrastructure.helpers.jdbc;

import com.example.service.user.infrastructure.helpers.jdbc.sqlstatement.TechnicalException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomNamedParameterJdbcTemplate {

    private static final String ERROR_GETTING_FIELD_AND_VALUE = "Error obteniendo el nombre y valor de objeto";
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void update(Object object, String sql) {
        MapSqlParameterSource paramSource = createParams(object);
        this.namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public void batchUpdate(List<?> objectList, String sql) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(objectList.toArray());
        this.namedParameterJdbcTemplate.batchUpdate(sql, batch);
    }

    public long updateAndReturnId(Object object, String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource paramSource = createParams(object);
        this.namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    private MapSqlParameterSource createParams(Object object) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                    field.setAccessible(true);
                    paramSource.addValue(field.getName(), field.get(object));
                    field.setAccessible(false);
                }
            } catch (Exception e) {
                throw new TechnicalException(ERROR_GETTING_FIELD_AND_VALUE);
            }
        }
        return paramSource;
    }

    public CustomNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }

}
