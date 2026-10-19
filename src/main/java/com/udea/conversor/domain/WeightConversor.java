package com.udea.conversor.domain;

import org.springframework.stereotype.Service;

import static com.udea.conversor.domain.Validaciones.validarMinimo;
import static com.udea.conversor.domain.Validaciones.validarNumero;

@Service
public class WeightConversor {

    // Factor de conversion: 1 libra = 453.59237 gramos (definicion internacional exacta)
    private static final double GRAMOS_POR_LIBRA = 453.59237;

    /**
     * Convierte gramos a libras.
     * Formula: libras = gramos / 453.59237
     */
    public double gramosALibras(double gramos) {
        validar(gramos, UnidadPeso.GRAMOS);
        return gramos / GRAMOS_POR_LIBRA;
    }

    /**
     * Convierte libras a gramos.
     * Formula: gramos = libras * 453.59237
     */
    public double librasAGramos(double libras) {
        validar(libras, UnidadPeso.LIBRAS);
        return libras * GRAMOS_POR_LIBRA;
    }

    /**
     * Convierte un valor entre dos unidades de peso.
     * Si origen y destino coinciden, devuelve el mismo valor (ya validado).
     */
    public double convertir(UnidadPeso origen, UnidadPeso destino, double valor) {
        validar(valor, origen);
        if (origen == destino) {
            return valor;
        }
        return origen == UnidadPeso.GRAMOS ? gramosALibras(valor) : librasAGramos(valor);
    }

    /**
     * Rechaza NaN, infinitos y pesos negativos (la masa no puede ser negativa).
     */
    private static void validar(double valor, UnidadPeso unidad) {
        String nombre = unidad.nombre();
        validarNumero(valor, nombre);
        validarMinimo(valor, 0.0, nombre);
    }
}
