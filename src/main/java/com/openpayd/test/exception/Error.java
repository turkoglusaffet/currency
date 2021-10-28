package com.openpayd.test.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Error {
    NON_EXISTENT_CURRENCY("currencyNotFound", "Currency '%s' is not found."),
    REST_CLIENT_EXCEPTION("restClient", "There is a problem when get currency");

    private final String errorCode;
    private final String errorMessage;
}
