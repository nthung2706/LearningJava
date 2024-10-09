package com.softz.identity.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.softz.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(
            RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(4000);
        apiResponse.setMessage(exception.getMessage());

        return ResponseEntity.badRequest()
                .body(apiResponse);
    }
    
}
