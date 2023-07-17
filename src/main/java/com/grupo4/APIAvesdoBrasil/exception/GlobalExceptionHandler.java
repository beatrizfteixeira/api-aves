package com.grupo4.APIAvesdoBrasil.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(Instant.now());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setError("Type Mismatch");
        errorDetails.setMessage("Invalid parameter type for " + exception.getName());
        errorDetails.setPath(webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    // handle especific exceptions

    //    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(new Date(),
//                "No Birds Found Here",
//                webRequest.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BirdNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFound(BirdNotFoundException e, HttpServletRequest request) {
    ErrorDetails error = new ErrorDetails();
            error.setTimestamp(Instant.now());
            error.setStatus(HttpStatus.NOT_FOUND.value());
            error.setError("Resource Not found");
            error.setMessage(e.getMessage());
            error.setPath(request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BirdSaveException.class)
    public ResponseEntity<ErrorDetails> handleInvalidSave(BirdSaveException e, HttpServletRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Invalid information");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BirdDeleteNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleBirdForDeleteNotFound(BirdDeleteNotFoundException e, HttpServletRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Bird not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BirdDeleteIdInvalidException.class)
    public ResponseEntity<ErrorDetails> handleBirdForDeleteInvalidId(BirdDeleteIdInvalidException e, HttpServletRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError("Invalid ID");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BirdUpdateException.class)
    public ResponseEntity<ErrorDetails> handleBirdUpdateNotFound(BirdUpdateException e, HttpServletRequest request) {
        ErrorDetails error = new ErrorDetails();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError("Bird not found");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BirdAPIException.class)
    public ResponseEntity<ErrorDetails> handleBidAPIException(BirdAPIException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
    }

}


