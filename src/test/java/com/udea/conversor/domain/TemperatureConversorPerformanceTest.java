package com.udea.conversor.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Performance tests for TemperatureConversor.
 *
 * These tests do not verify correctness (that's covered by TemperatureConversorTest);
 * they verify that each conversion method can handle a large volume of calls within
 * an acceptable time budget. They are tagged "performance" so they can be excluded
 * from routine unit-test runs and executed separately, e.g.:
 *
 *   ./gradlew performanceTest   (to run only these)
 *   ./gradlew test              (excludes them automatically)
 *
 * Thresholds are intentionally generous to avoid flaky failures on slower CI machines;
 * tighten them if you need stricter performance guarantees.
 */
@Tag("performance")
class TemperatureConversorPerformanceTest {

    private TemperatureConversor conversor;
    private static final int ITERACIONES = 1_000_000;
    private static final long LIMITE_MS = 500; // 0.5 seconds for 1M calls

    @BeforeEach
    void setUp() {
        conversor = new TemperatureConversor();
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Celsius a Fahrenheit deben completarse en menos de 500 ms")
    void testRendimiento_CelsiusAFahrenheit() {
        // Arrange
        double celsius = 25.0;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.celsiusAFahrenheit(celsius);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Celsius->Fahrenheit tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Fahrenheit a Celsius deben completarse en menos de 500 ms")
    void testRendimiento_FahrenheitACelsius() {
        // Arrange
        double fahrenheit = 77.0;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.fahrenheitACelsius(fahrenheit);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Fahrenheit->Celsius tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Fahrenheit a Kelvin deben completarse en menos de 500 ms")
    void testRendimiento_FahrenheitAKelvin() {
        // Arrange
        double fahrenheit = 32.0;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.fahrenheitAKelvin(fahrenheit);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Fahrenheit->Kelvin tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Celsius a Kelvin deben completarse en menos de 500 ms")
    void testRendimiento_CelsiusAKelvin() {
        // Arrange
        double celsius = 0.0;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.celsiusAKelvin(celsius);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Celsius->Kelvin tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Kelvin a Celsius deben completarse en menos de 500 ms")
    void testRendimiento_KelvinACelsius() {
        // Arrange
        double kelvin = 273.15;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.kelvinACelsius(kelvin);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Kelvin->Celsius tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Kelvin a Fahrenheit deben completarse en menos de 500 ms")
    void testRendimiento_KelvinAFahrenheit() {
        // Arrange
        double kelvin = 273.15;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.kelvinAFahrenheit(kelvin);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Kelvin->Fahrenheit tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: llamadas mixtas alternando los 6 metodos deben completarse en menos de 3s")
    void testRendimiento_llamadasMixtas() {
        // Arrange
        int iteracionesPorMetodo = ITERACIONES / 6;
        long limiteMixtoMs = 3000;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < iteracionesPorMetodo; i++) {
            conversor.celsiusAFahrenheit(20.0);
            conversor.fahrenheitACelsius(68.0);
            conversor.fahrenheitAKelvin(68.0);
            conversor.celsiusAKelvin(20.0);
            conversor.kelvinACelsius(293.15);
            conversor.kelvinAFahrenheit(293.15);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < limiteMixtoMs,
                "Las llamadas mixtas tardaron " + finMs + "ms, se esperaba menos de " + limiteMixtoMs + "ms");
    }
}