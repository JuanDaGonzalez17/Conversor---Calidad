package Conversor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for WeightConversor.
 * Each test follows the AAA pattern: Arrange, Act, Assert.
 */
class WeightConversorTest {

    private WeightConversor conversor;
    private static final double DELTA = 0.0001;

    @BeforeEach
    void setUp() {
        conversor = new WeightConversor();
    }

    // ---------------------------------------------------------
    // Gramos -> Libras
    // ---------------------------------------------------------

    @Test
    @DisplayName("Gramos a Libras - valor comun (453.59237g -> 1lb)")
    void testGramosALibras_valorComun() {
        // Arrange
        double gramos = 453.59237;
        double esperado = 1.0;

        // Act
        double resultado = conversor.convertirGramos_Libras(gramos);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Gramos a Libras - cero gramos (0g -> 0lb)")
    void testGramosALibras_cero() {
        // Arrange
        double gramos = 0.0;
        double esperado = 0.0;

        // Act
        double resultado = conversor.convertirGramos_Libras(gramos);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Gramos a Libras - valor grande (1000000g -> ~2204.62lb)")
    void testGramosALibras_valorGrande() {
        // Arrange
        double gramos = 1_000_000.0;
        double esperado = 2204.622622;

        // Act
        double resultado = conversor.convertirGramos_Libras(gramos);

        // Assert
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    @DisplayName("Gramos a Libras - valor pequeno (1g -> ~0.0022lb)")
    void testGramosALibras_valorPequeno() {
        // Arrange
        double gramos = 1.0;
        double esperado = 0.00220462;

        // Act
        double resultado = conversor.convertirGramos_Libras(gramos);

        // Assert
        assertEquals(esperado, resultado, 0.00001);
    }

    @Test
    @DisplayName("Gramos a Libras - valor negativo lanza excepcion")
    void testGramosALibras_negativo_lanzaExcepcion() {
        // Arrange
        double gramos = -50.0;

        // Act
        Executable operacion = () -> conversor.convertirGramos_Libras(gramos);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Gramos a Libras - NaN lanza excepcion")
    void testGramosALibras_NaN_lanzaExcepcion() {
        // Arrange
        double gramos = Double.NaN;

        // Act
        Executable operacion = () -> conversor.convertirGramos_Libras(gramos);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Gramos a Libras - infinito positivo lanza excepcion")
    void testGramosALibras_infinitoPositivo_lanzaExcepcion() {
        // Arrange
        double gramos = Double.POSITIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.convertirGramos_Libras(gramos);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Gramos a Libras - infinito negativo lanza excepcion")
    void testGramosALibras_infinitoNegativo_lanzaExcepcion() {
        // Arrange
        double gramos = Double.NEGATIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.convertirGramos_Libras(gramos);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Libras -> Gramos
    // ---------------------------------------------------------

    @Test
    @DisplayName("Libras a Gramos - valor comun (1lb -> 453.59237g)")
    void testLibrasAGramos_valorComun() {
        // Arrange
        double libras = 1.0;
        double esperado = 453.59237;

        // Act
        double resultado = conversor.convertirLibras_Gramos(libras);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Libras a Gramos - cero libras (0lb -> 0g)")
    void testLibrasAGramos_cero() {
        // Arrange
        double libras = 0.0;
        double esperado = 0.0;

        // Act
        double resultado = conversor.convertirLibras_Gramos(libras);

        // Assert
        assertEquals(esperado, resultado, DELTA);
    }

    @Test
    @DisplayName("Libras a Gramos - valor grande (2204.622622lb -> ~1000000g)")
    void testLibrasAGramos_valorGrande() {
        // Arrange
        double libras = 2204.622622;
        double esperado = 1_000_000.0;

        // Act
        double resultado = conversor.convertirLibras_Gramos(libras);

        // Assert
        assertEquals(esperado, resultado, 0.1);
    }

    @Test
    @DisplayName("Libras a Gramos - valor negativo lanza excepcion")
    void testLibrasAGramos_negativo_lanzaExcepcion() {
        // Arrange
        double libras = -1.0;

        // Act
        Executable operacion = () -> conversor.convertirLibras_Gramos(libras);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Libras a Gramos - NaN lanza excepcion")
    void testLibrasAGramos_NaN_lanzaExcepcion() {
        // Arrange
        double libras = Double.NaN;

        // Act
        Executable operacion = () -> conversor.convertirLibras_Gramos(libras);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    @Test
    @DisplayName("Libras a Gramos - infinito lanza excepcion")
    void testLibrasAGramos_infinito_lanzaExcepcion() {
        // Arrange
        double libras = Double.POSITIVE_INFINITY;

        // Act
        Executable operacion = () -> conversor.convertirLibras_Gramos(libras);

        // Assert
        assertThrows(IllegalArgumentException.class, operacion);
    }

    // ---------------------------------------------------------
    // Round-trip consistency tests
    // ---------------------------------------------------------

    @Test
    @DisplayName("Ida y vuelta Gramos -> Libras -> Gramos mantiene el valor original")
    void testRoundTrip_GramosLibrasGramos() {
        // Arrange
        double gramosOriginal = 750.0;

        // Act
        double libras = conversor.convertirGramos_Libras(gramosOriginal);
        double gramosFinal = conversor.convertirLibras_Gramos(libras);

        // Assert
        assertEquals(gramosOriginal, gramosFinal, DELTA);
    }

    @Test
    @DisplayName("Ida y vuelta Libras -> Gramos -> Libras mantiene el valor original")
    void testRoundTrip_LibrasGramosLibras() {
        // Arrange
        double librasOriginal = 5.5;

        // Act
        double gramos = conversor.convertirLibras_Gramos(librasOriginal);
        double librasFinal = conversor.convertirGramos_Libras(gramos);

        // Assert
        assertEquals(librasOriginal, librasFinal, DELTA);
    }
}
