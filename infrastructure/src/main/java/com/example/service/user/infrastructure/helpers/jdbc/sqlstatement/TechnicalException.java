package com.example.service.user.infrastructure.helpers.jdbc.sqlstatement;

public class TechnicalException extends RuntimeException {
    public TechnicalException(String message) {
        super(message);
    }
}
