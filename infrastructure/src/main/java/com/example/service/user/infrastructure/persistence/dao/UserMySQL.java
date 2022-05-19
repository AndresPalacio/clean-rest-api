package com.example.service.user.infrastructure.persistence.dao;


import com.example.service.user.infrastructure.helpers.jdbc.CustomNamedParameterJdbcTemplate;
import com.example.service.user.infrastructure.helpers.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class UserMySQL {

    private CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(value = "")
    private String userSelect;


    public UserMySQL(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    private void change(){
        MapSqlParameterSource mapSqlParameterSource;

    }

}
