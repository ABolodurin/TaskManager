package ru.abolodurin.taskmanager.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.abolodurin.taskmanager.model.dto.ErrorResponse;
import ru.abolodurin.taskmanager.model.entity.CommonException;

import java.util.Objects;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(CommonException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorResponse(
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthenticationException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            DataIntegrityViolationException e) {
        if (e.getMessage().contains("users_email_key")) return new ResponseEntity<>(
                new ErrorResponse("this email is already registered"), HttpStatus.BAD_REQUEST);

        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Service error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParseException(HttpMessageNotReadableException e) {
        if (e.getMessage().contains("Task$Status")) return new ResponseEntity<>(
                new ErrorResponse("Wrong status. Correct options: " +
                        e.getMessage().substring(e.getMessage().indexOf('['))), HttpStatus.BAD_REQUEST);

        if (e.getMessage().contains("Task$Priority")) return new ResponseEntity<>(
                new ErrorResponse("Wrong priority. Correct options: " +
                        e.getMessage().substring(e.getMessage().indexOf('['))), HttpStatus.BAD_REQUEST);

        if (e.getMessage().contains("JSON parse error")) return new ResponseEntity<>(
                new ErrorResponse("JSON parse error"), HttpStatus.BAD_REQUEST);

        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Service error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUncommonException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("Service error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
