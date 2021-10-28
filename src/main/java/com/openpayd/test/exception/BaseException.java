package com.openpayd.test.exception;
import java.util.Objects;


public abstract class BaseException extends RuntimeException {
    private final String field;
    private final String code;
    private final String message;

    public BaseException(String errorCode, String message) {
        this("", errorCode, message);
    }

    public BaseException(String field, String errorCode, String message) {
        super(message);
        Objects.requireNonNull(errorCode);
        this.code = errorCode;
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return this.field;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
