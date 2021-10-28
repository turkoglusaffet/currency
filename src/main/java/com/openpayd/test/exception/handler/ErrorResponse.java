package com.openpayd.test.exception.handler;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ErrorResponse
{
    public ErrorResponse(String errorCode, String message) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }

    private String errorCode;

    private String message;

}