package com.Shop.Shop.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExceptionHandlerNotFound.class)
    public ResponseEntity<RequestError> HandleExceptionNotFound(ExceptionHandlerNotFound exceptionHandlerNotFound
    , HttpServletRequest request){
         var requestError = RequestError.builder()
                 .message(exceptionHandlerNotFound.getMessage())

                 .path(request.getRequestURI())
                 .status((short)404)
                 .timestamp(LocalDateTime.now())
                 .build();
         return ResponseEntity.status((short)404).body(requestError);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RequestError> HandleInternalServerError(Exception ex
    ,HttpServletRequest request){
        var requestError = RequestError.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .status((short)500)
                .build();
        return ResponseEntity.status(requestError.getStatus()).body(requestError);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestError> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest req) {
        var messageError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(" ; "));
        var errors = RequestError.builder()
                .timestamp(LocalDateTime.now())
                .message(messageError)
                .status((short)400)
                .path(req.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
