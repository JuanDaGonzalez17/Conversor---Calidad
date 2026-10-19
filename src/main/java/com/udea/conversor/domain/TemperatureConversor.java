package com.udea.conversor.domain;

import org.springframework.stereotype.Service;

import static com.udea.conversor.domain.Validaciones.validarMinimo;
import static com.udea.conversor.domain.Validaciones.validarNumero;

@Service
public class TemperatureConversor {

    // Cero absoluto en cada escala, usado para validar
    private static final double CERO_ABSOLUTO_CELSIUS = -273.15;
    private static final double CERO_ABSOLUTO_FAHRENHEIT = -459.67;
    private static final double CERO_ABSOLUTO_KELVIN = 0.0;

    /**
     * Convierte Celsius a Fahrenheit.
     * Formula: F = (C * 9/5) + 32
     */
    public double celsiusAFahrenheit(double celsius) {
        validar(celsius, EscalaTemperatura.CELSIUS);
        return (celsius * 9.0 / 5.0) + 32;
    }

    /**
     * Convierte Fahrenheit a Celsius.
     * Formula: C = (F - 32) * 5/9
     */
    public double fahrenheitACelsius(double fahrenheit) {
        validar(fahrenheit, EscalaTemperatura.FAHRENHEIT);
        return (fahrenheit - 32) * 5.0 / 9.0;
    }

    /**
     * Convierte Fahrenheit a Kelvin.
     * Formula: K = (F - 32) * 5/9 + 273.15
     */
    public double fahrenheitAKelvin(double fahrenheit) {
        validar(fahrenheit, EscalaTemperatura.FAHRENHEIT);
        return (fahrenheit - 32) * 5.0 / 9.0 + 273.15;
    }

    /**
     * Convierte Celsius a Kelvin.
     * Formula: K = C + 273.15
     */
    public double celsiusAKelvin(double celsius) {
        validar(celsius, EscalaTemperatura.CELSIUS);
        return celsius + 273.15;
    }

    /**
     * Convierte Kelvin a Celsius.
     * Formula: C = K - 273.15
     */
    public double kelvinACelsius(double kelvin) {
        validar(kelvin, EscalaTemperatura.KELVIN);
        return kelvin - 273.15;
    }

    /**
     * Convierte Kelvin a Fahrenheit.
     * Formula: F = K * 9/5 - 459.67
     */
    public double kelvinAFahrenheit(double kelvin) {
        validar(kelvin, EscalaTemperatura.KELVIN);
        return kelvin * 9.0 / 5.0 - 459.67;
    }

    /**
     * Convierte un valor entre dos escalas cualesquiera.
     * Si origen y destino coinciden, devuelve el mismo valor (ya validado).
     */
    public double convertir(EscalaTemperatura origen, EscalaTemperatura destino, double valor) {
        validar(valor, origen);
        if (origen == destino) {
            return valor;
        }
        return switch (origen) {
            case CELSIUS -> destino == EscalaTemperatura.FAHRENHEIT ? celsiusAFahrenheit(valor) : celsiusAKelvin(valor);
            case FAHRENHEIT -> destino == EscalaTemperatura.CELSIUS ? fahrenheitACelsius(valor) : fahrenheitAKelvin(valor);
            case KELVIN -> destino == EscalaTemperatura.CELSIUS ? kelvinACelsius(valor) : kelvinAFahrenheit(valor);
        };
    }

    private static void validar(double valor, EscalaTemperatura escala) {
        String unidad = escala.nombre();
        validarNumero(valor, unidad);
        validarMinimo(valor, ceroAbsoluto(escala), unidad);
    }

    private static double ceroAbsoluto(EscalaTemperatura escala) {
        return switch (escala) {
            case CELSIUS -> CERO_ABSOLUTO_CELSIUS;
            case FAHRENHEIT -> CERO_ABSOLUTO_FAHRENHEIT;
            case KELVIN -> CERO_ABSOLUTO_KELVIN;
        };
    }
}
