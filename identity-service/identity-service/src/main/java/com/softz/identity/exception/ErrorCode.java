package com.softz.identity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    METHODARGUMENT_NOTVALIDEXCEPTION(9999, "MethodArgument NotValidException", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(404100, "User not found", HttpStatus.NOT_FOUND),
    USER_EXISTED(409100, "User %s existed", HttpStatus.CONFLICT),
    INVALID_USERNAME(100100, "Username must be in between {min} and {max}", HttpStatus.BAD_REQUEST),
    INVALID_FIELD(100102, "{field} must be in between {min} and {max}", HttpStatus.BAD_REQUEST),
    INVALID_NOTNULL(100100, "{field} cant null", HttpStatus.BAD_REQUEST),
    MISSING_MESSAGE_KEY(100101, "Invalid message", HttpStatus.BAD_REQUEST),
    INVALID_DATE_OF_BIRTH(100103, "User's age must be equal or greater than {min}", HttpStatus.BAD_REQUEST),
    INVALID_INPUT(9000, "{field} invalid input", HttpStatus.BAD_REQUEST),
    USER_ID_NOT_FOUND(404102, "User %s not found", HttpStatus.NOT_FOUND),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

}
