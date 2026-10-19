package com.udea.conversor.domain;

/**
 * Validaciones compartidas por los conversores.
 */
final class Validaciones {

    /** Tolerancia para errores de redondeo en coma flotante. */
    private static final double EPSILON = 1e-9;

    private Validaciones() {
        // Clase utilitaria: no se instancia
    }

    /**
     * Rechaza NaN e infinitos, que no son medidas validas.
     */
    static void validarNumero(double valor, String unidad) {
        if (Double.isNaN(valor)) {
            throw new IllegalArgumentException("El valor en " + unidad + " no puede ser NaN.");
        }
        if (Double.isInfinite(valor)) {
            throw new IllegalArgumentException("El valor en " + unidad + " no puede ser infinito.");
        }
    }

    /**
     * Rechaza valores por debajo de un minimo fisico (por ejemplo, el cero absoluto).
     */
    static void validarMinimo(double valor, double minimo, String unidad) {
        if (valor < minimo - EPSILON) {
            throw new IllegalArgumentException(
                    "El valor en " + unidad + " (" + valor + ") esta por debajo del minimo permitido ("
                            + minimo + " " + unidad + ").");
        }
    }
}
