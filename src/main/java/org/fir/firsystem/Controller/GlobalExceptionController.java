package org.fir.firsystem.Controller;


import org.fir.firsystem.exception.ComplaintNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ComplaintNotFoundException.class)
    public ResponseEntity<?> complaintNotFound(ComplaintNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    
}
