package com.example.service.user.infrastructure.jdbc.sqlstatement;

public class TechnicalException extends RuntimeException {
    public TechnicalException(String message) {
        super(message);
    }
}
