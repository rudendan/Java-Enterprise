package com.example.homework6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorBody> handleException(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorBody("ERROR", e.getMessage())
                , HttpStatus.NOT_FOUND);
    }


    static class ErrorBody {

        private String name;
        private String details;

        public ErrorBody(String name, String details) {
            this.name = name;
            this.details = details;
        }

        public String getName() {
            return name;
        }

        public String getDetails() {
            return details;
        }
    }
}
