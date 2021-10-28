package com.openpayd.test.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RestClientException extends BaseException {
    public RestClientException(String errorCode, String errorMessage, Object... parameters) {
        super(errorCode, String.format(errorMessage, parameters));
    }
}