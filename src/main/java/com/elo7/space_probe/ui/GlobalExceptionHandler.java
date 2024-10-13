package com.elo7.space_probe.ui;

import com.elo7.space_probe.app.exceptions.PositionOutOfPlanetBoundariesException;
import com.elo7.space_probe.app.exceptions.ResourceNotFoundException;
import com.elo7.space_probe.app.exceptions.ProbeCollisionException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.BindErrorUtils;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    ErrorMessageDTO handleException(Exception exception, HttpServletRequest request) {
        return new ErrorMessageDTO(exception.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    ErrorMessageDTO handleException(ResourceNotFoundException exception, HttpServletRequest request) {
        return new ErrorMessageDTO(exception.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler({PositionOutOfPlanetBoundariesException.class, ProbeCollisionException.class, IllegalArgumentException.class})
    @ResponseStatus(value = BAD_REQUEST)
    ErrorMessageDTO handleException(RuntimeException exception, HttpServletRequest request) {
        return new ErrorMessageDTO(exception.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = BAD_REQUEST)
    ErrorMessageDTO handleException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        String errorMessage = exception.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        return new ErrorMessageDTO(errorMessage, BAD_REQUEST);
    }

}
