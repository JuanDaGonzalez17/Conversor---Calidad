package com.udea.conversor.api;

/**
 * Cuerpo JSON de una conversion exitosa.
 * Ejemplo: {"origen":"celsius","destino":"fahrenheit","valor":25.0,"resultado":77.0}
 */
public record RespuestaConversion(String origen, String destino, double valor, double resultado) {
}
