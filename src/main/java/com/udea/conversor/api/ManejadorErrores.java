package com.udea.conversor.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Traduce los errores de validacion del dominio a respuestas HTTP 400.
 */
@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RespuestaError valorInvalido(IllegalArgumentException ex) {
        return new RespuestaError(ex.getMessage());
    }
}
