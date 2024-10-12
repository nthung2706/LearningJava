package com.softz.identity.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.softz.dto.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(
            Exception exception) {
        log.error("something error code", exception);

        
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode())
                .body(apiResponse);
    }

    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {
        log.error("something error code", exception);
        String key = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(key);

        ApiResponse apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity.status(errorCode.xgetStatusCode())
                .body(apiResponse);
    }
    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(
            AppException exception) {
        ApiResponse apiResponse = new ApiResponse();
        ErrorCode errorCode = exception.getErrorCode();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest()
                .body(apiResponse);
    }
    
  
}
