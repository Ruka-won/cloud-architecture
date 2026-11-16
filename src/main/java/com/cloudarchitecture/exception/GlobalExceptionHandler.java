package com.cloudarchitecture.exception;

import com.cloudarchitecture.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExcpetion(Exception e) {

        log.error("[ERROR - LOG] unhandled exception : {}", e.getLocalizedMessage(), e);

        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다.");

        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        
    }
}
