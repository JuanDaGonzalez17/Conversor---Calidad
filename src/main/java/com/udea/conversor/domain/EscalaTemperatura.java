package com.udea.conversor.domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Escalas de temperatura soportadas por el conversor.
 */
public enum EscalaTemperatura {
    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    /**
     * Obtiene la escala a partir de su nombre, sin distinguir mayusculas.
     *
     * @throws IllegalArgumentException si la escala no existe
     */
    public static EscalaTemperatura desdeTexto(String texto) {
        return Arrays.stream(values())
                .filter(escala -> escala.name().equalsIgnoreCase(texto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Escala de temperatura no soportada: '" + texto + "'. Valores validos: " + nombresValidos()));
    }

    public String nombre() {
        return name().toLowerCase(Locale.ROOT);
    }

    private static String nombresValidos() {
        return Arrays.stream(values()).map(EscalaTemperatura::nombre).collect(Collectors.joining(", "));
    }
}
