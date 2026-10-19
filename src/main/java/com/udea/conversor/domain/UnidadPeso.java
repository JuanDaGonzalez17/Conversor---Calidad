package com.udea.conversor.domain;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Unidades de peso soportadas por el conversor.
 */
public enum UnidadPeso {
    GRAMOS,
    LIBRAS;

    /**
     * Obtiene la unidad a partir de su nombre, sin distinguir mayusculas.
     *
     * @throws IllegalArgumentException si la unidad no existe
     */
    public static UnidadPeso desdeTexto(String texto) {
        return Arrays.stream(values())
                .filter(unidad -> unidad.name().equalsIgnoreCase(texto))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unidad de peso no soportada: '" + texto + "'. Valores validos: " + nombresValidos()));
    }

    public String nombre() {
        return name().toLowerCase(Locale.ROOT);
    }

    private static String nombresValidos() {
        return Arrays.stream(values()).map(UnidadPeso::nombre).collect(Collectors.joining(", "));
    }
}
