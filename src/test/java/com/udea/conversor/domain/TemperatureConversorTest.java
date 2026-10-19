package com.udea.conversor.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for TemperatureConversor.
 * Each test follows the AAA pattern: Arrange, Act, Assert.
 */
class TemperatureConversorTest {

    private TemperatureConversor conversor;
    private static final double DELTA = 0.0001;

    @BeforeEach
    void setUp() {
        conversor = new TemperatureConversor();
    }

    // ---------------------------------------------------------
    // Celsius -> Fahrenheit
    // ---------------------------------------------------------

    @Test
    @DisplayName("Celsius a Fahrenheit - valor positivo comun (25C -> 77F)")
    void testCelsiusAFahrenheit_valorPositivo() {
        // Arrange
        double celsius = 25.0;
        double esperado = 77.0;

        // Act
        double resultado = conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Celsius a Fahrenheit - punto de congelacion (0C -> 32F)")
    void testCelsiusAFahrenheit_puntoCongelacion() {
        // Arrange
        double celsius = 0.0;
        double esperado = 32.0;

        // Act
        double resultado = conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Celsius a Fahrenheit - valor negativo, punto de cruce (-40C -> -40F)")
    void testCelsiusAFahrenheit_puntoDeCruce() {
        // Arrange
        double celsius = -40.0;
        double esperado = -40.0;

        // Act
        double resultado = conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Celsius a Fahrenheit - cero absoluto exacto (-273.15C -> -459.67F)")
    void testCelsiusAFahrenheit_ceroAbsoluto() {
        // Arrange
        double celsius = -273.15;
        double esperado = -459.67;

        // Act
        double resultado = conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Celsius a Fahrenheit - por debajo del cero absoluto lanza excepcion")
    void testCelsiusAFahrenheit_debajoDeCeroAbsoluto_lanzaExcepcion() {
        // Arrange
        double celsius = -300.0;

        // Act
        Executable operacion = () -> conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Celsius a Fahrenheit - NaN lanza excepcion")
    void testCelsiusAFahrenheit_NaN_lanzaExcepcion() {
        // Arrange
        double celsius = Double.NaN;

        // Act
        Executable operacion = () -> conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Celsius a Fahrenheit - infinito positivo lanza excepcion")
    void testCelsiusAFahrenheit_infinitoPositivo_lanzaExcepcion() {
        // Arrange
        double celsius = Double.POSITIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Celsius a Fahrenheit - infinito negativo lanza excepcion")
    void testCelsiusAFahrenheit_infinitoNegativo_lanzaExcepcion() {
        // Arrange
        double celsius = Double.NEGATIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.celsiusAFahrenheit(celsius);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Fahrenheit -> Celsius
    // ---------------------------------------------------------

    @Test
    @DisplayName("Fahrenheit a Celsius - valor positivo comun (77F -> 25C)")
    void testFahrenheitACelsius_valorPositivo() {
        // Arrange
        double fahrenheit = 77.0;
        double esperado = 25.0;

        // Act
        double resultado = conversor.fahrenheitACelsius(fahrenheit);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Fahrenheit a Celsius - punto de congelacion (32F -> 0C)")
    void testFahrenheitACelsius_puntoCongelacion() {
        // Arrange
        double fahrenheit = 32.0;
        double esperado = 0.0;

        // Act
        double resultado = conversor.fahrenheitACelsius(fahrenheit);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Fahrenheit a Celsius - cero absoluto exacto (-459.67F -> -273.15C)")
    void testFahrenheitACelsius_ceroAbsoluto() {
        // Arrange
        double fahrenheit = -459.67;
        double esperado = -273.15;

        // Act
        double resultado = conversor.fahrenheitACelsius(fahrenheit);

        // Assert
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Fahrenheit a Celsius - por debajo del cero absoluto lanza excepcion")
    void testFahrenheitACelsius_debajoDeCeroAbsoluto_lanzaExcepcion() {
        // Arrange
        double fahrenheit = -500.0;

        // Act
        Executable operacion = () -> conversor.fahrenheitACelsius(fahrenheit);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Fahrenheit a Celsius - NaN lanza excepcion")
    void testFahrenheitACelsius_NaN_lanzaExcepcion() {
        // Arrange
        double fahrenheit = Double.NaN;

        // Act
        Executable operacion = () -> conversor.fahrenheitACelsius(fahrenheit);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Fahrenheit a Celsius - infinito lanza excepcion")
    void testFahrenheitACelsius_infinito_lanzaExcepcion() {
        // Arrange
        double fahrenheit = Double.POSITIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.fahrenheitACelsius(fahrenheit);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Fahrenheit -> Kelvin
    // ---------------------------------------------------------

    @Test
    @DisplayName("Fahrenheit a Kelvin - valor comun (32F -> 273.15K)")
    void testFahrenheitAKelvin_valorComun() {
        // Arrange
        double fahrenheit = 32.0;
        double esperado = 273.15;

        // Act
        double resultado = conversor.fahrenheitAKelvin(fahrenheit);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Fahrenheit a Kelvin - cero absoluto exacto (-459.67F -> 0K)")
    void testFahrenheitAKelvin_ceroAbsoluto() {
        // Arrange
        double fahrenheit = -459.67;
        double esperado = 0.0;

        // Act
        double resultado = conversor.fahrenheitAKelvin(fahrenheit);

        // Assert
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Fahrenheit a Kelvin - por debajo del cero absoluto lanza excepcion")
    void testFahrenheitAKelvin_debajoDeCeroAbsoluto_lanzaExcepcion() {
        // Arrange
        double fahrenheit = -460.0;

        // Act
        Executable operacion = () -> conversor.fahrenheitAKelvin(fahrenheit);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Fahrenheit a Kelvin - NaN lanza excepcion")
    void testFahrenheitAKelvin_NaN_lanzaExcepcion() {
        // Arrange
        double fahrenheit = Double.NaN;

        // Act
        Executable operacion = () -> conversor.fahrenheitAKelvin(fahrenheit);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Celsius -> Kelvin
    // ---------------------------------------------------------

    @Test
    @DisplayName("Celsius a Kelvin - valor comun (0C -> 273.15K)")
    void testCelsiusAKelvin_valorComun() {
        // Arrange
        double celsius = 0.0;
        double esperado = 273.15;

        // Act
        double resultado = conversor.celsiusAKelvin(celsius);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Celsius a Kelvin - cero absoluto exacto (-273.15C -> 0K)")
    void testCelsiusAKelvin_ceroAbsoluto() {
        // Arrange
        double celsius = -273.15;
        double esperado = 0.0;

        // Act
        double resultado = conversor.celsiusAKelvin(celsius);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Celsius a Kelvin - por debajo del cero absoluto lanza excepcion")
    void testCelsiusAKelvin_debajoDeCeroAbsoluto_lanzaExcepcion() {
        // Arrange
        double celsius = -274.0;

        // Act
        Executable operacion = () -> conversor.celsiusAKelvin(celsius);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Celsius a Kelvin - infinito negativo lanza excepcion")
    void testCelsiusAKelvin_infinitoNegativo_lanzaExcepcion() {
        // Arrange
        double celsius = Double.NEGATIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.celsiusAKelvin(celsius);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Kelvin -> Celsius
    // ---------------------------------------------------------

    @Test
    @DisplayName("Kelvin a Celsius - valor comun (273.15K -> 0C)")
    void testKelvinACelsius_valorComun() {
        // Arrange
        double kelvin = 273.15;
        double esperado = 0.0;

        // Act
        double resultado = conversor.kelvinACelsius(kelvin);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Kelvin a Celsius - cero Kelvin exacto (0K -> -273.15C)")
    void testKelvinACelsius_ceroKelvin() {
        // Arrange
        double kelvin = 0.0;
        double esperado = -273.15;

        // Act
        double resultado = conversor.kelvinACelsius(kelvin);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Kelvin a Celsius - valor negativo (imposible fisicamente) lanza excepcion")
    void testKelvinACelsius_negativo_lanzaExcepcion() {
        // Arrange
        double kelvin = -1.0;

        // Act
        Executable operacion = () -> conversor.kelvinACelsius(kelvin);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Kelvin a Celsius - NaN lanza excepcion")
    void testKelvinACelsius_NaN_lanzaExcepcion() {
        // Arrange
        double kelvin = Double.NaN;

        // Act
        Executable operacion = () -> conversor.kelvinACelsius(kelvin);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Kelvin -> Fahrenheit
    // ---------------------------------------------------------

    @Test
    @DisplayName("Kelvin a Fahrenheit - valor comun (273.15K -> 32F)")
    void testKelvinAFahrenheit_valorComun() {
        // Arrange
        double kelvin = 273.15;
        double esperado = 32.0;

        // Act
        double resultado = conversor.kelvinAFahrenheit(kelvin);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Kelvin a Fahrenheit - cero Kelvin exacto (0K -> -459.67F)")
    void testKelvinAFahrenheit_ceroKelvin() {
        // Arrange
        double kelvin = 0.0;
        double esperado = -459.67;

        // Act
        double resultado = conversor.kelvinAFahrenheit(kelvin);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Kelvin a Fahrenheit - valor negativo lanza excepcion")
    void testKelvinAFahrenheit_negativo_lanzaExcepcion() {
        // Arrange
        double kelvin = -10.0;

        // Act
        Executable operacion = () -> conversor.kelvinAFahrenheit(kelvin);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Kelvin a Fahrenheit - infinito lanza excepcion")
    void testKelvinAFahrenheit_infinito_lanzaExcepcion() {
        // Arrange
        double kelvin = Double.POSITIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.kelvinAFahrenheit(kelvin);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Round-trip consistency tests
    // ---------------------------------------------------------

    @Test
    @DisplayName("Ida y vuelta Celsius -> Fahrenheit -> Celsius mantiene el valor original")
    void testRoundTrip_CelsiusFahrenheitCelsius() {
        // Arrange
        double celsiusOriginal = 36.6;

        // Act
        double fahrenheit = conversor.celsiusAFahrenheit(celsiusOriginal);
        double celsiusFinal = conversor.fahrenheitACelsius(fahrenheit);

        // Assert
        assertEquals(celsiusOriginal, celsiusFinal, DELTA);
    }

    @Test
    @DisplayName("Ida y vuelta Celsius -> Kelvin -> Celsius mantiene el valor original")
    void testRoundTrip_CelsiusKelvinCelsius() {
        // Arrange
        double celsiusOriginal = 100.0;

        // Act
        double kelvin = conversor.celsiusAKelvin(celsiusOriginal);
        double celsiusFinal = conversor.kelvinACelsius(kelvin);

        // Assert
        assertEquals(celsiusOriginal, celsiusFinal, DELTA);
    }

    @Test
    @DisplayName("Ida y vuelta Fahrenheit -> Kelvin -> Fahrenheit mantiene el valor original")
    void testRoundTrip_FahrenheitKelvinFahrenheit() {
        // Arrange
        double fahrenheitOriginal = 98.6;

        // Act
        double kelvin = conversor.fahrenheitAKelvin(fahrenheitOriginal);
        double fahrenheitFinal = conversor.kelvinAFahrenheit(kelvin);

        // Assert
        assertEquals(fahrenheitOriginal, fahrenheitFinal, DELTA);
    }
}
