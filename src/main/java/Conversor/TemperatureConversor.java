package Conversor;

public class TemperatureConversor {

    // Absolute zero limits in each scale, used for validation
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;
    private static final double ABSOLUTE_ZERO_KELVIN = 0.0;

    /**
     * Converts Celsius to Fahrenheit.
     * Formula: F = (C * 9/5) + 32
     */
    public double convertirCelsius_Farenheit(double celsius) {
        validarNumero(celsius, "Celsius");
        validarLimite(celsius, ABSOLUTE_ZERO_CELSIUS, "Celsius");
        return (celsius * 9.0 / 5.0) + 32;
    }

    /**
     * Converts Fahrenheit to Celsius.
     * Formula: C = (F - 32) * 5/9
     */
    public double convertirFahrenheit_Celsius(double farenheit) {
        validarNumero(farenheit, "Fahrenheit");
        validarLimite(farenheit, ABSOLUTE_ZERO_FAHRENHEIT, "Fahrenheit");
        return (farenheit - 32) * 5.0 / 9.0;
    }

    /**
     * Converts Fahrenheit to Kelvin.
     * Formula: K = (F - 32) * 5/9 + 273.15
     */
    public double convertirFahrenheit_Kelvin(double fahrenheit) {
        validarNumero(fahrenheit, "Fahrenheit");
        validarLimite(fahrenheit, ABSOLUTE_ZERO_FAHRENHEIT, "Fahrenheit");
        return (fahrenheit - 32) * 5.0 / 9.0 + 273.15;
    }

    /**
     * Converts Celsius to Kelvin.
     * Formula: K = C + 273.15
     */
    public double convertirCelsius_Kelvin(double celsius) {
        validarNumero(celsius, "Celsius");
        validarLimite(celsius, ABSOLUTE_ZERO_CELSIUS, "Celsius");
        return celsius + 273.15;
    }

    /**
     * Converts Kelvin to Celsius.
     * Formula: C = K - 273.15
     */
    public double convertirKelvin_Celsius(double kelvin) {
        validarNumero(kelvin, "Kelvin");
        validarLimite(kelvin, ABSOLUTE_ZERO_KELVIN, "Kelvin");
        return kelvin - 273.15;
    }

    /**
     * Converts Kelvin to Fahrenheit.
     * Formula: F = K * 9/5 - 459.67
     */
    public double convertirKelvin_Fahrenheit(double kelvin) {
        validarNumero(kelvin, "Kelvin");
        validarLimite(kelvin, ABSOLUTE_ZERO_KELVIN, "Kelvin");
        return kelvin * 9.0 / 5.0 - 459.67;
    }

    // ---------- Helper validation methods ----------

    /**
     * Rejects NaN and Infinite values, which are not valid temperatures.
     */
    private void validarNumero(double valor, String escala) {
        if (Double.isNaN(valor)) {
            throw new IllegalArgumentException("El valor en " + escala + " no puede ser NaN.");
        }
        if (Double.isInfinite(valor)) {
            throw new IllegalArgumentException("El valor en " + escala + " no puede ser infinito.");
        }
    }

    /**
     * Rejects temperatures physically below absolute zero for the given scale.
     * A small epsilon is used to tolerate floating-point rounding errors.
     */
    private void validarLimite(double valor, double minimo, String escala) {
        final double EPSILON = 1e-9;
        if (valor < minimo - EPSILON) {
            throw new IllegalArgumentException(
                    "El valor en " + escala + " (" + valor + ") esta por debajo del cero absoluto ("
                            + minimo + " " + escala + ").");
        }
    }
}