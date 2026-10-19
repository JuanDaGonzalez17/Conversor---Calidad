package com.udea.conversor.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas del metodo generico convertir(origen, destino, valor) y de los enums de unidades.
 */
class ConversionGenericaTest {

    private static final double DELTA = 0.0001;

    private final TemperatureConversor temperatura = new TemperatureConversor();
    private final WeightConversor peso = new WeightConversor();

    @ParameterizedTest(name = "{0} -> {1}: {2} = {3}")
    @DisplayName("Temperatura - todas las combinaciones de escalas")
    @CsvSource({
            "CELSIUS,    FAHRENHEIT, 25,     77",
            "CELSIUS,    KELVIN,     0,      273.15",
            "CELSIUS,    CELSIUS,    36.6,   36.6",
            "FAHRENHEIT, CELSIUS,    77,     25",
            "FAHRENHEIT, KELVIN,     32,     273.15",
            "FAHRENHEIT, FAHRENHEIT, 100,    100",
            "KELVIN,     CELSIUS,    273.15, 0",
            "KELVIN,     FAHRENHEIT, 273.15, 32",
            "KELVIN,     KELVIN,     10,     10"
    })
    void convertirTemperatura(EscalaTemperatura origen, EscalaTemperatura destino, double valor, double esperado) {
        // Act
        double resultado = temperatura.convertir(origen, destino, valor);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Temperatura - misma escala bajo el cero absoluto lanza excepcion")
    void convertirTemperatura_mismaEscalaInvalida_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> temperatura.convertir(EscalaTemperatura.KELVIN, EscalaTemperatura.KELVIN, -1));
    }

    @ParameterizedTest(name = "{0} -> {1}: {2} = {3}")
    @DisplayName("Peso - todas las combinaciones de unidades")
    @CsvSource({
            "GRAMOS, LIBRAS, 453.59237, 1",
            "LIBRAS, GRAMOS, 1,         453.59237",
            "GRAMOS, GRAMOS, 750,       750",
            "LIBRAS, LIBRAS, 2,         2"
    })
    void convertirPeso(UnidadPeso origen, UnidadPeso destino, double valor, double esperado) {
        // Act
        double resultado = peso.convertir(origen, destino, valor);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Peso - misma unidad con valor negativo lanza excepcion")
    void convertirPeso_mismaUnidadNegativa_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> peso.convertir(UnidadPeso.LIBRAS, UnidadPeso.LIBRAS, -5));
    }

    @Test
    @DisplayName("EscalaTemperatura.desdeTexto no distingue mayusculas")
    void escalaDesdeTexto_ignoraMayusculas() {
        assertEquals(EscalaTemperatura.KELVIN, EscalaTemperatura.desdeTexto("KeLvIn"));
        assertEquals("kelvin", EscalaTemperatura.KELVIN.nombre());
    }

    @ParameterizedTest
    @DisplayName("EscalaTemperatura.desdeTexto rechaza escalas desconocidas")
    @ValueSource(strings = {"rankine", "", "celsiuss"})
    void escalaDesdeTexto_desconocida_lanzaExcepcion(String texto) {
        assertThrows(IllegalArgumentException.class, () -> EscalaTemperatura.desdeTexto(texto));
    }

    @Test
    @DisplayName("UnidadPeso.desdeTexto no distingue mayusculas")
    void unidadDesdeTexto_ignoraMayusculas() {
        assertEquals(UnidadPeso.GRAMOS, UnidadPeso.desdeTexto("Gramos"));
        assertEquals("libras", UnidadPeso.LIBRAS.nombre());
    }

    @ParameterizedTest
    @DisplayName("UnidadPeso.desdeTexto rechaza unidades desconocidas")
    @ValueSource(strings = {"kilos", "", "onzas"})
    void unidadDesdeTexto_desconocida_lanzaExcepcion(String texto) {
        assertThrows(IllegalArgumentException.class, () -> UnidadPeso.desdeTexto(texto));
    }
}
