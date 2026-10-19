package com.udea.conversor.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Performance tests for WeightConversor.
 *
 * Verifies that each conversion method can handle a large volume of calls within
 * an acceptable time budget. Tagged "performance" so it can be run/excluded separately
 * from routine unit tests, e.g.:
 *
 *   ./gradlew performanceTest   (to run only these)
 *   ./gradlew test              (excludes them automatically)
 */
@Tag("performance")
class WeightConversorPerformanceTest {

    private WeightConversor conversor;
    private static final int ITERACIONES = 1_000_000;
    private static final long LIMITE_MS = 500; // 0.5 seconds for 1M calls

    @BeforeEach
    void setUp() {
        conversor = new WeightConversor();
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Gramos a Libras deben completarse en menos de 500 ms")
    void testRendimiento_GramosALibras() {
        // Arrange
        double gramos = 500.0;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.gramosALibras(gramos);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Gramos->Libras tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: 1M conversiones Libras a Gramos deben completarse en menos de 500 ms")
    void testRendimiento_LibrasAGramos() {
        // Arrange
        double libras = 1.5;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACIONES; i++) {
            conversor.librasAGramos(libras);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La conversion Libras->Gramos tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: llamadas mixtas alternando ambos metodos deben completarse en menos de 500 ms")
    void testRendimiento_llamadasMixtas() {
        // Arrange
        int iteracionesPorMetodo = ITERACIONES / 2;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < iteracionesPorMetodo; i++) {
            conversor.gramosALibras(1000.0);
            conversor.librasAGramos(2.2);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "Las llamadas mixtas tardaron " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }

    @Test
    @DisplayName("Rendimiento: rafaga de conversiones con valores variables debe completarse en menos de 500 ms")
    void testRendimiento_valoresVariables() {
        // Arrange
        int totalLlamadas = ITERACIONES;

        // Act
        long inicio = System.nanoTime();
        for (int i = 0; i < totalLlamadas; i++) {
            double valor = (i % 1000) + 0.5;
            conversor.gramosALibras(valor);
        }
        long finMs = (System.nanoTime() - inicio) / 1_000_000;

        // Assert
        assertTrue(finMs < LIMITE_MS,
                "La rafaga con valores variables tardo " + finMs + "ms, se esperaba menos de " + LIMITE_MS + "ms");
    }
}