package com.udea.conversor.api;

/**
 * Cuerpo JSON de una peticion rechazada.
 * Ejemplo: {"error":"El valor en kelvin (-1.0) esta por debajo del minimo permitido (0.0 kelvin)."}
 */
public record RespuestaError(String error) {
}
