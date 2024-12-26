package com.bank.alfalah.exception.constant;


import com.bank.alfalah.exception.ErrorPrinter;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum implements ErrorPrinter {

    INVALID_PARAMETER(HttpStatus.NOT_ACCEPTABLE),
    INVALID_CATEGORY(HttpStatus.NOT_ACCEPTABLE),
    ENTITY_NOT_FOUND(HttpStatus.NOT_ACCEPTABLE),
    X_PAY_FAILED(HttpStatus.NOT_ACCEPTABLE),
    INVALID_REQUEST(HttpStatus.NOT_ACCEPTABLE),
    FILE_UPLOAD_FAILED(HttpStatus.NOT_ACCEPTABLE),
    STORE_ALREADY_EXIST(HttpStatus.NOT_ACCEPTABLE),
	UN_AUTHORIZED(HttpStatus.UNAUTHORIZED);

    private final HttpStatus httpStatus;

    ErrorCodeEnum(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}