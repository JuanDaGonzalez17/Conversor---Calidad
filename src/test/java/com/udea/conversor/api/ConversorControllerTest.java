package com.udea.conversor.api;

import com.udea.conversor.domain.TemperatureConversor;
import com.udea.conversor.domain.WeightConversor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas unitarias del controller (sin levantar Spring).
 * Las pruebas HTTP de punta a punta estan en src/test/java/acceptance (Karate).
 */
class ConversorControllerTest {

    private static final double DELTA = 0.0001;

    private final ConversorController controller =
            new ConversorController(new TemperatureConversor(), new WeightConversor());

    @Test
    @DisplayName("Temperatura - devuelve escalas normalizadas, valor y resultado")
    void convertirTemperatura_valido() {
        // Act
        RespuestaConversion respuesta = controller.convertirTemperatura("Celsius", "FAHRENHEIT", 25);

        // Assert
        assertEquals("celsius", respuesta.origen());
        assertEquals("fahrenheit", respuesta.destino());
        assertEquals(25, respuesta.valor(), DELTA);
        assertEquals(77, respuesta.resultado(), DELTA);
    }

    @Test
    @DisplayName("Temperatura - escala desconocida lanza IllegalArgumentException")
    void convertirTemperatura_escalaDesconocida() {
        assertThrows(IllegalArgumentException.class,
                () -> controller.convertirTemperatura("rankine", "celsius", 10));
    }

    @Test
    @DisplayName("Peso - devuelve unidades normalizadas, valor y resultado")
    void convertirPeso_valido() {
        // Act
        RespuestaConversion respuesta = controller.convertirPeso("libras", "gramos", 1);

        // Assert
        assertEquals("libras", respuesta.origen());
        assertEquals("gramos", respuesta.destino());
        assertEquals(1, respuesta.valor(), DELTA);
        assertEquals(453.59237, respuesta.resultado(), DELTA);
    }

    @Test
    @DisplayName("Peso - valor negativo lanza IllegalArgumentException")
    void convertirPeso_negativo() {
        assertThrows(IllegalArgumentException.class,
                () -> controller.convertirPeso("gramos", "libras", -1));
    }

    @Test
    @DisplayName("ManejadorErrores - convierte la excepcion en un cuerpo con el mensaje")
    void manejadorErrores_devuelveMensaje() {
        // Arrange
        ManejadorErrores manejador = new ManejadorErrores();

        // Act
        RespuestaError error = manejador.valorInvalido(new IllegalArgumentException("mensaje de prueba"));

        // Assert
        assertEquals("mensaje de prueba", error.error());
    }
}
